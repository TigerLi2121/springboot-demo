package com.mm.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mm.modules.job.entity.ScheduleJobEntity;

/**
 * 定时任务
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 保存定时任务
     */
    void saveJob(ScheduleJobEntity scheduleJobEntity);

    /**
     * 更新定时任务
     */
    void update(ScheduleJobEntity scheduleJobEntity);

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
