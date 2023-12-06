package com.mm.modules.job.utils;

import com.mm.common.utils.SpringContextUtils;
import com.mm.common.utils.ThrowableUtils;
import com.mm.modules.job.entity.ScheduleJobEntity;
import com.mm.modules.job.entity.ScheduleJobLogEntity;
import com.mm.modules.job.service.ScheduleJobLogService;
import com.mm.modules.job.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务
 *
 * @author tigerli
 * @date 2019-09-09
 */
@Slf4j
@Async
public class ExecutionJob extends QuartzJobBean {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ScheduleJobEntity scheduleJobEntity = (ScheduleJobEntity) context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_KEY);
        // 获取spring bean
        ScheduleJobLogService scheduleJobLogService = SpringContextUtils.getBean("scheduleLogService", ScheduleJobLogService.class);
        ScheduleJobService scheduleJobService = SpringContextUtils.getBean("scheduleJobService", ScheduleJobService.class);

        ScheduleJobLogEntity slog = new ScheduleJobLogEntity();
        slog.setJobId(scheduleJobEntity.getId());
        slog.setJobName(scheduleJobEntity.getJobName());
        slog.setBeanName(scheduleJobEntity.getBeanName());
        slog.setMethodName(scheduleJobEntity.getMethodName());
        slog.setParams(scheduleJobEntity.getParams());
        slog.setCronExpression(scheduleJobEntity.getCronExpression());
        slog.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            log.info("任务准备执行，任务名称：{}", scheduleJobEntity.getJobName());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJobEntity.getBeanName(), scheduleJobEntity.getMethodName(),
                    scheduleJobEntity.getParams());
            Future<?> future = executorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            slog.setTime(times);
            // 任务状态
            slog.setIsSuccess(true);
            log.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", scheduleJobEntity.getJobName(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务名称：", scheduleJobEntity.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            slog.setTime(times);
            // 任务状态
            slog.setIsSuccess(false);
            slog.setExceptionDetail(ThrowableUtils.getStackTrace(e));
            //更新状态
            scheduleJobService.updateBatch(new Long[]{scheduleJobEntity.getId()}, false);
        } finally {
            scheduleJobLogService.save(slog);
        }
    }
}
