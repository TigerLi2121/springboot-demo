package com.mm.modules.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mm.modules.quartz.dao.QuartzJobDao;
import com.mm.modules.quartz.entity.QuartzJob;
import com.mm.modules.quartz.service.QuartzJobService;
import com.mm.modules.quartz.utils.QuartzManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

@Service("quartzJobService")
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobDao, QuartzJob> implements QuartzJobService {


    @Autowired
    private QuartzManage quartzManage;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJob(QuartzJob quartzJob) {
        this.save(quartzJob);
        quartzManage.addJob(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuartzJob quartzJob) {
        this.updateById(quartzJob);
        quartzManage.updateJobCron(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            quartzManage.deleteJob(jobId);
        }
        //删除数据
        this.removeByIds(Arrays.asList(jobIds));
    }

    @Override
    public boolean updateBatch(Long[] jobIds, Boolean status) {
        Collection<QuartzJob> jobs = this.listByIds(Arrays.asList(jobIds));
        for (QuartzJob job : jobs) {
            if (status) {
                quartzManage.resumeJob(job);
                job.setIsPause(false);
            } else {
                quartzManage.pauseJob(job.getId());
                job.setIsPause(true);
            }
        }
        return this.updateBatchById(jobs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        Collection<QuartzJob> jobs = this.listByIds(Arrays.asList(jobIds));
        for (QuartzJob job : jobs) {
            quartzManage.runJobNow(job);
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
