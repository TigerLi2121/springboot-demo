package com.mm.modules.job.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mm.modules.job.entity.ScheduleJob;
import com.mm.modules.job.service.ScheduleJobService;
import com.mm.modules.job.utils.ScheduleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shmily
 * @date 2019-09-09
 */
@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private ScheduleManage scheduleManage;

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("--------------------注入定时任务---------------------");
        List<ScheduleJob> scheduleJobs = scheduleJobService.list(
                new QueryWrapper<ScheduleJob>().ge("is_pause", false));
        scheduleJobs.forEach(quartzJob -> {
            scheduleManage.addJob(quartzJob);
        });
        System.out.println("--------------------定时任务注入完成---------------------");
    }
}
