package com.mm.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mm.modules.job.dao.ScheduleJobLogDao;
import com.mm.modules.job.entity.ScheduleJobLogEntity;
import com.mm.modules.job.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;

@Service("scheduleLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

}
