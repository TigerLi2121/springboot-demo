package com.mm.modules.quartz.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mm.modules.quartz.entity.QuartzJob;
import com.mm.modules.quartz.service.QuartzJobService;
import com.mm.modules.quartz.utils.QuartzManage;
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
    private QuartzJobService quartzJobService;

    @Autowired
    private QuartzManage quartzManage;

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobs = quartzJobService.list(
                new QueryWrapper<QuartzJob>().ge("is_pause", false));
        quartzJobs.forEach(quartzJob -> {
            quartzManage.addJob(quartzJob);
        });
        System.out.println("--------------------定时任务注入完成---------------------");
    }
}
