package com.mm.modules.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class QuartzJob implements Serializable {

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
     * 状态
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