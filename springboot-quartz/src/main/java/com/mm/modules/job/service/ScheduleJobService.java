package com.mm.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mm.modules.job.entity.ScheduleJob;

/**
 * 定时任务
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

    /**
     * 保存定时任务
     */
    void saveJob(ScheduleJob scheduleJob);

    /**
     * 更新定时任务
     */
    void update(ScheduleJob scheduleJob);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    boolean updateBatch(Long[] jobIds, Boolean status);

    /**
     * 立即执行
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     */
    void resume(Long[] jobIds);
}
