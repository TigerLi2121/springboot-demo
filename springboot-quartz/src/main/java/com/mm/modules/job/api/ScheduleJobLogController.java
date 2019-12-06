package com.mm.modules.job.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.common.utils.R;
import com.mm.modules.job.entity.ScheduleJobLogEntity;
import com.mm.modules.job.service.ScheduleJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务日志
 */
@Slf4j
@RestController
@RequestMapping("/quartzLog")
public class ScheduleJobLogController {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @GetMapping("/list")
    public R list(Long page, Long limit, String jobId) {
        log.info("list page:{} limit:{} jobId:{}", page, limit, jobId);
        IPage<ScheduleJobLogEntity> iPage = scheduleJobLogService.page(new Page<>(page, limit),
                new QueryWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId), "job_id", jobId));
        return R.ok().put("list", iPage.getRecords()).put("total", iPage.getTotal());
    }

    /**
     * 定时任务日志信息
     */
    @GetMapping("/info/{logId}")
    public R info(@PathVariable("logId") Long logId) {
        ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);
        return R.ok().put("log", log);
    }
}
