package com.mm.modules.quartz.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.common.utils.R;
import com.mm.modules.quartz.entity.QuartzJob;
import com.mm.modules.quartz.service.QuartzJobService;
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
public class QuartzJobController {

    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 定时任务列表
     */
    @GetMapping("/list")
    public R list(Long page, Long limit, String beanName) {
        log.info("list page:{} limit:{} beanName:{}", page, limit, beanName);
        IPage<QuartzJob> iPage = quartzJobService.page(new Page<>(page, limit),
                new QueryWrapper<QuartzJob>().like(StringUtils.isNotBlank(beanName), "bean_name", beanName));
        return R.ok().put("list", iPage.getRecords()).put("total", iPage.getTotal());
    }

    /**
     * 定时任务信息
     */
    @GetMapping("/info/{jobId}")
    public R info(@PathVariable("jobId") Long jobId) {
        log.info("info jobId:{}", jobId);
        QuartzJob job = quartzJobService.getById(jobId);
        return R.ok().put("job", job);
    }

    /**
     * 保存定时任务
     */
    @PostMapping("/save")
    public R save(@RequestBody QuartzJob quartzJob) {
        log.info("save quartzJob:{}", quartzJob);
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            return R.error("cron表达式格式错误");
        }
        quartzJob.setCreateTime(new Date());
        quartzJobService.saveJob(quartzJob);
        return R.ok();
    }

    /**
     * 修改定时任务
     */
    @PostMapping("/update")
    public R update(@RequestBody QuartzJob quartzJob) {
        log.info("update quartzJob:{}", quartzJob);
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            return R.error("cron表达式格式错误");
        }
        quartzJob.setCreateTime(new Date());
        quartzJobService.update(quartzJob);
        return R.ok();
    }

    /**
     * 删除定时任务
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] jobIds) {
        log.info("delete jobIds:{}", jobIds);
        quartzJobService.deleteBatch(jobIds);
        return R.ok();
    }

    /**
     * 立即执行任务
     */
    @PostMapping("/run")
    public R run(@RequestBody Long[] jobIds) {
        log.info("run jobIds:{}", jobIds);
        quartzJobService.run(jobIds);
        return R.ok();
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pause")
    public R pause(@RequestBody Long[] jobIds) {
        log.info("pause jobIds:{}", jobIds);
        quartzJobService.pause(jobIds);
        return R.ok();
    }

    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
    public R resume(@RequestBody Long[] jobIds) {
        log.info("resume jobIds:{}", jobIds);
        quartzJobService.resume(jobIds);
        return R.ok();
    }

}
