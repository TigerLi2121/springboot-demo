package com.mm.modules.job.utils;

import com.mm.common.exception.BadException;
import com.mm.modules.job.entity.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务操作
 *
 * @author shmily
 * @date 2019-09-09
 */
@Slf4j
@Component
public class ScheduleManage {

    private static final String JOB_NAME = "TASK_";

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    public void addJob(ScheduleJob scheduleJob) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                    withIdentity(JOB_NAME + scheduleJob.getId()).build();

            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + scheduleJob.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()))
                    .build();

            cronTrigger.getJobDataMap().put(ScheduleJob.JOB_KEY, scheduleJob);

            //重置启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail, cronTrigger);

            // 暂停任务
            if (scheduleJob.getIsPause()) {
                pauseJob(scheduleJob.getId());
            }
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
            throw new BadException("创建定时任务失败");
        }
    }

    /**
     * 更新job cron表达式
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + scheduleJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null) {
                addJob(scheduleJob);
                trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            }
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置启动时间
            ((CronTriggerImpl) trigger).setStartTime(new Date());
            trigger.getJobDataMap().put(ScheduleJob.JOB_KEY, scheduleJob);

            scheduler.rescheduleJob(triggerKey, trigger);
            // 暂停任务
            if (scheduleJob.getIsPause()) {
                pauseJob(scheduleJob.getId());
            }
        } catch (Exception e) {
            log.error("更新定时任务失败", e);
            throw new BadException("更新定时任务失败");
        }

    }

    /**
     * 删除一个job
     *
     * @param jobId
     * @throws SchedulerException
     */
    public void deleteJob(Long jobId) {
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + jobId);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            log.error("删除定时任务失败", e);
            throw new BadException("删除定时任务失败");
        }
    }

    /**
     * 恢复一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void resumeJob(ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + scheduleJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null)
                addJob(scheduleJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + scheduleJob.getId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            log.error("恢复定时任务失败", e);
            throw new BadException("恢复定时任务失败");
        }
    }

    /**
     * 立即执行job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void runJobNow(ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + scheduleJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null)
                addJob(scheduleJob);
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleJob.JOB_KEY, scheduleJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + scheduleJob.getId());
            scheduler.triggerJob(jobKey, dataMap);
        } catch (Exception e) {
            log.error("定时任务执行失败", e);
            throw new BadException("定时任务执行失败");
        }
    }

    /**
     * 暂停一个job
     *
     * @param jobId
     * @throws SchedulerException
     */
    public void pauseJob(Long jobId) {
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + jobId);
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务暂停失败", e);
            throw new BadException("定时任务暂停失败");
        }
    }
}
