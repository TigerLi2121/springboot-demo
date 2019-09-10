package com.mm.modules.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @author shmily
 * @date 2019-09-09
 */
@Data
public class QuartzLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 状态
     */
    private Boolean isSuccess;

    /**
     * Bean名称
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 异常详细
     */
    private String exceptionDetail;

    /**
     * 耗时（毫秒）
     */
    private Long time;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
