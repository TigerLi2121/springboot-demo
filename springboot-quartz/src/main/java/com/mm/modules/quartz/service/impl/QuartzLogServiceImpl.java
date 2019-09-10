package com.mm.modules.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mm.modules.quartz.dao.QuartzLogDao;
import com.mm.modules.quartz.entity.QuartzLog;
import com.mm.modules.quartz.service.QuartzLogService;
import org.springframework.stereotype.Service;

@Service("quartzLogService")
public class QuartzLogServiceImpl extends ServiceImpl<QuartzLogDao, QuartzLog> implements QuartzLogService {

}
