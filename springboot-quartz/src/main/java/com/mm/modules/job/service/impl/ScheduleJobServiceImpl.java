package com.mm.modules.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mm.modules.job.dao.ScheduleJobDao;
import com.mm.modules.job.entity.ScheduleJobEntity;
import com.mm.modules.job.service.ScheduleJobService;
import com.mm.modules.job.utils.ScheduleManage;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {

    @Autowired
    private ScheduleManage scheduleManage;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        System.out.println("--------------------注入定时任务---------------------");
        List<ScheduleJobEntity> scheduleJobEntityList = this.list(
                new QueryWrapper<ScheduleJobEntity>().ge("is_pause", false));
        for (ScheduleJobEntity scheduleJobEntity : scheduleJobEntityList) {
            CronTrigger cronTrigger = scheduleManage.getCronTrigger(scheduleJobEntity.getId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                scheduleManage.addJob(scheduleJobEntity);
            } else {
                scheduleManage.updateJobCron(scheduleJobEntity);
            }
        }
        System.out.println("--------------------定时任务注入完成---------------------");
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJob(ScheduleJobEntity scheduleJobEntity) {
        this.save(scheduleJobEntity);
        scheduleManage.addJob(scheduleJobEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobEntity scheduleJobEntity) {
        this.updateById(scheduleJobEntity);
        scheduleManage.updateJobCron(scheduleJobEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            scheduleManage.deleteJob(jobId);
        }
        //删除数据
        this.removeByIds(Arrays.asList(jobIds));
    }

    @Override
    public boolean updateBatch(Long[] jobIds, Boolean status) {
        Collection<ScheduleJobEntity> jobs = this.listByIds(Arrays.asList(jobIds));
        for (ScheduleJobEntity job : jobs) {
            if (status) {
                scheduleManage.resumeJob(job);
                job.setIsPause(false);
            } else {
                scheduleManage.pauseJob(job.getId());
                job.setIsPause(true);
            }
        }
        return this.updateBatchById(jobs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        Collection<ScheduleJobEntity> jobs = this.listByIds(Arrays.asList(jobIds));
        for (ScheduleJobEntity job : jobs) {
            scheduleManage.runJobNow(job);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        updateBatch(jobIds, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        updateBatch(jobIds, true);
    }

}
