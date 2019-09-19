package com.mm.modules.job.utils;

import com.mm.common.utils.SpringContextUtils;
import com.mm.common.utils.ThrowableUtils;
import com.mm.modules.job.entity.ScheduleJob;
import com.mm.modules.job.entity.ScheduleLog;
import com.mm.modules.job.service.ScheduleJobService;
import com.mm.modules.job.service.ScheduleLogService;
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
 * @author shmily
 * @date 2019-09-09
 */
@Slf4j
@Async
public class ExecutionJob extends QuartzJobBean {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_KEY);
        // 获取spring bean
        ScheduleLogService scheduleLogService = SpringContextUtils.getBean("scheduleLogService", ScheduleLogService.class);
        ScheduleJobService scheduleJobService = SpringContextUtils.getBean("scheduleJobService", ScheduleJobService.class);

        ScheduleLog slog = new ScheduleLog();
        slog.setJobId(scheduleJob.getId());
        slog.setJobName(scheduleJob.getJobName());
        slog.setBeanName(scheduleJob.getBeanName());
        slog.setMethodName(scheduleJob.getMethodName());
        slog.setParams(scheduleJob.getParams());
        slog.setCronExpression(scheduleJob.getCronExpression());
        slog.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            log.info("任务准备执行，任务名称：{}", scheduleJob.getJobName());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(),
                    scheduleJob.getParams());
            Future<?> future = executorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            slog.setTime(times);
            // 任务状态
            slog.setIsSuccess(true);
            log.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", scheduleJob.getJobName(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务名称：{}" + scheduleJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            slog.setTime(times);
            // 任务状态
            slog.setIsSuccess(false);
            slog.setExceptionDetail(ThrowableUtils.getStackTrace(e));
            //更新状态
            scheduleJobService.updateBatch(new Long[]{scheduleJob.getId()}, false);
        } finally {
            scheduleLogService.save(slog);
        }
    }
}
