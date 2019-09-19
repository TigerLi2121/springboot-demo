package com.mm.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mm.modules.job.dao.ScheduleLogDao;
import com.mm.modules.job.entity.ScheduleLog;
import com.mm.modules.job.service.ScheduleLogService;
import org.springframework.stereotype.Service;

@Service("scheduleLogService")
public class ScheduleLogServiceImpl extends ServiceImpl<ScheduleLogDao, ScheduleLog> implements ScheduleLogService {

}
