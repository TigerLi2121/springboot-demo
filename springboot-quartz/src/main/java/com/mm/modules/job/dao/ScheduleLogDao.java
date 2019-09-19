package com.mm.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mm.modules.job.entity.ScheduleLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 */
@Mapper
public interface ScheduleLogDao extends BaseMapper<ScheduleLog> {
}
