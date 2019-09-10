package com.mm.modules.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mm.modules.quartz.entity.QuartzLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 */
@Mapper
public interface QuartzLogDao extends BaseMapper<QuartzLog> {
}
