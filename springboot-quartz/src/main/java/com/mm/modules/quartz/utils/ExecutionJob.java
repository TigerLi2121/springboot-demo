package com.mm.modules.quartz.utils;

import com.mm.common.utils.SpringContextUtils;
import com.mm.common.utils.ThrowableUtils;
import com.mm.modules.quartz.entity.QuartzJob;
import com.mm.modules.quartz.entity.QuartzLog;
import com.mm.modules.quartz.service.QuartzJobService;
import com.mm.modules.quartz.service.QuartzLogService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Async
public class ExecutionJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzLogService quartzLogService = SpringContextUtils.getBean("quartzLogService", QuartzLogService.class);
        QuartzJobService quartzJobService = SpringContextUtils.getBean("quartzJobService", QuartzJobService.class);

        QuartzLog log = new QuartzLog();
        log.setJobId(quartzJob.getId());
        log.setJobName(quartzJob.getJobName());
        log.setBeanName(quartzJob.getBeanName());
        log.setMethodName(quartzJob.getMethodName());
        log.setParams(quartzJob.getParams());
        log.setCronExpression(quartzJob.getCronExpression());
        log.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            logger.info("任务准备执行，任务名称：{}", quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = executorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态
            log.setIsSuccess(true);
            logger.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", quartzJob.getJobName(), times);
        } catch (Exception e) {
            logger.error("任务执行失败，任务名称：{}" + quartzJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtils.getStackTrace(e));
            //更新状态
            quartzJobService.updateBatch(new Long[]{quartzJob.getId()}, false);
        } finally {
            quartzLogService.save(log);
        }
    }
}
