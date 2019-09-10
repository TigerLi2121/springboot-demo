package com.mm.modules.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mm.modules.quartz.entity.QuartzJob;

/**
 * 定时任务
 */
public interface QuartzJobService extends IService<QuartzJob> {

    /**
     * 保存定时任务
     */
    void saveJob(QuartzJob quartzJob);

    /**
     * 更新定时任务
     */
    void update(QuartzJob quartzJob);

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
