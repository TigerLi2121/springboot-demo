package com.mm.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mm.modules.job.dao.ScheduleJobDao;
import com.mm.modules.job.entity.ScheduleJob;
import com.mm.modules.job.service.ScheduleJobService;
import com.mm.modules.job.utils.ScheduleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJob> implements ScheduleJobService {


    @Autowired
    private ScheduleManage scheduleManage;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJob(ScheduleJob scheduleJob) {
        this.save(scheduleJob);
        scheduleManage.addJob(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJob scheduleJob) {
        this.updateById(scheduleJob);
        scheduleManage.updateJobCron(scheduleJob);
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
        Collection<ScheduleJob> jobs = this.listByIds(Arrays.asList(jobIds));
        for (ScheduleJob job : jobs) {
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
        Collection<ScheduleJob> jobs = this.listByIds(Arrays.asList(jobIds));
        for (ScheduleJob job : jobs) {
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
