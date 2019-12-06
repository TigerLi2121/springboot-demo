package com.mm.modules.job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 * @author shmily
 * @date 2019-09-09
 */
@Data
@TableName("schedule_job")
public class ScheduleJobEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String JOB_KEY = "JOB_KEY";

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 定时器名称
     */
    private String jobName;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    /**
     * 状态：1暂停、0启用
     */
    private Boolean isPause = false;

    /**
     * Bean名称
     */
    @NotBlank(message = "bean名称不能为空")
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
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}