package com.mm.modules.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mm.modules.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务
 */
@Mapper
public interface QuartzJobDao extends BaseMapper<QuartzJob> {

}
