package com.mm.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mm.modules.job.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务
 */
@Mapper
public interface ScheduleJobDao extends BaseMapper<ScheduleJob> {

}
