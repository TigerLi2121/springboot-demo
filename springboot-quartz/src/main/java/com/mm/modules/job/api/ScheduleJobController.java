package com.mm.modules.job.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.common.utils.R;
import com.mm.modules.job.entity.ScheduleJob;
import com.mm.modules.job.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 定时任务
 */
@Slf4j
@RestController
@RequestMapping("/quartzJob")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @GetMapping("/list")
    public R list(Long page, Long limit, String beanName) {
        log.info("list page:{} limit:{} beanName:{}", page, limit, beanName);
        IPage<ScheduleJob> iPage = scheduleJobService.page(new Page<>(page, limit),
                new QueryWrapper<ScheduleJob>().like(StringUtils.isNotBlank(beanName), "bean_name", beanName));
        return R.ok().put("list", iPage.getRecords()).put("total", iPage.getTotal());
    }

    /**
     * 定时任务信息
     */
    @GetMapping("/info/{jobId}")
    public R info(@PathVariable("jobId") Long jobId) {
        log.info("info jobId:{}", jobId);
        ScheduleJob job = scheduleJobService.getById(jobId);
        return R.ok().put("job", job);
    }

    /**
     * 保存定时任务
     */
    @PostMapping("/save")
    public R save(@RequestBody ScheduleJob scheduleJob) {
        log.info("save quartzJob:{}", scheduleJob);
        if (!CronExpression.isValidExpression(scheduleJob.getCronExpression())) {
            return R.error("cron表达式格式错误");
        }
        scheduleJob.setCreateTime(new Date());
        scheduleJobService.saveJob(scheduleJob);
        return R.ok();
    }

    /**
     * 修改定时任务
     */
    @PostMapping("/update")
    public R update(@RequestBody ScheduleJob scheduleJob) {
        log.info("update quartzJob:{}", scheduleJob);
        if (!CronExpression.isValidExpression(scheduleJob.getCronExpression())) {
            return R.error("cron表达式格式错误");
        }
        scheduleJob.setCreateTime(new Date());
        scheduleJobService.update(scheduleJob);
        return R.ok();
    }

    /**
     * 删除定时任务
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] jobIds) {
        log.info("delete jobIds:{}", jobIds);
        scheduleJobService.deleteBatch(jobIds);
        return R.ok();
    }

    /**
     * 立即执行任务
     */
    @PostMapping("/run")
    public R run(@RequestBody Long[] jobIds) {
        log.info("run jobIds:{}", jobIds);
        scheduleJobService.run(jobIds);
        return R.ok();
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pause")
    public R pause(@RequestBody Long[] jobIds) {
        log.info("pause jobIds:{}", jobIds);
        scheduleJobService.pause(jobIds);
        return R.ok();
    }

    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
    public R resume(@RequestBody Long[] jobIds) {
        log.info("resume jobIds:{}", jobIds);
        scheduleJobService.resume(jobIds);
        return R.ok();
    }

}
