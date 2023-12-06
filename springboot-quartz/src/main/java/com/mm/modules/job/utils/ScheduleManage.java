package com.mm.modules.job.utils;

import com.mm.common.exception.BadException;
import com.mm.modules.job.entity.ScheduleJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务操作
 *
 * @author tigerli
 * @date 2019-09-09
 */
@Slf4j
@Component
public class ScheduleManage {

    private static final String JOB_NAME = "TASK_";

    @Qualifier("schedulerFactoryBean")
    @Autowired
    private Scheduler scheduler;

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public CronTrigger getCronTrigger(Long jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("获取定时任务CronTrigger出现异常", e);
            throw new BadException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 新增job
     *
     * @param scheduleJobEntity
     */
    public void addJob(ScheduleJobEntity scheduleJobEntity) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                    withIdentity(JOB_NAME + scheduleJobEntity.getId()).build();

            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + scheduleJobEntity.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJobEntity.getCronExpression()))
                    .build();

            cronTrigger.getJobDataMap().put(ScheduleJobEntity.JOB_KEY, scheduleJobEntity);

            //重置启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail, cronTrigger);

            // 暂停任务
            if (scheduleJobEntity.getIsPause()) {
                pauseJob(scheduleJobEntity.getId());
            }
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
            throw new BadException("创建定时任务失败");
        }
    }

    /**
     * 更新job cron表达式
     *
     * @param scheduleJobEntity
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJobEntity scheduleJobEntity) {
        try {
            CronTrigger trigger = getCronTrigger(scheduleJobEntity.getId());
            // 如果不存在则创建一个定时任务
            if (trigger == null) {
                addJob(scheduleJobEntity);
                trigger = getCronTrigger(scheduleJobEntity.getId());
            }
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobEntity.getCronExpression());
            trigger = trigger.getTriggerBuilder().withIdentity(getTriggerKey(scheduleJobEntity.getId())).withSchedule(scheduleBuilder).build();
            //重置启动时间
            ((CronTriggerImpl) trigger).setStartTime(new Date());
            trigger.getJobDataMap().put(ScheduleJobEntity.JOB_KEY, scheduleJobEntity);

            scheduler.rescheduleJob(getTriggerKey(scheduleJobEntity.getId()), trigger);
            // 暂停任务
            if (scheduleJobEntity.getIsPause()) {
                pauseJob(scheduleJobEntity.getId());
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
            JobKey jobKey = getJobKey(jobId);
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
     * @param scheduleJobEntity
     * @throws SchedulerException
     */
    public void resumeJob(ScheduleJobEntity scheduleJobEntity) {
        try {
            CronTrigger trigger = getCronTrigger(scheduleJobEntity.getId());
            // 如果不存在则创建一个定时任务
            if (trigger == null)
                addJob(scheduleJobEntity);
            JobKey jobKey = getJobKey(scheduleJobEntity.getId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            log.error("恢复定时任务失败", e);
            throw new BadException("恢复定时任务失败");
        }
    }

    /**
     * 立即执行job
     *
     * @param scheduleJobEntity
     * @throws SchedulerException
     */
    public void runJobNow(ScheduleJobEntity scheduleJobEntity) {
        try {
            CronTrigger trigger = getCronTrigger(scheduleJobEntity.getId());
            // 如果不存在则创建一个定时任务
            if (trigger == null)
                addJob(scheduleJobEntity);
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleJobEntity.JOB_KEY, scheduleJobEntity);
            JobKey jobKey = getJobKey(scheduleJobEntity.getId());
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
            JobKey jobKey = getJobKey(jobId);
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务暂停失败", e);
            throw new BadException("定时任务暂停失败");
        }
    }
}
