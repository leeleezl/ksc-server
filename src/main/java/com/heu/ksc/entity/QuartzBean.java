package com.heu.ksc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class QuartzBean extends BaseVo {
    /** 任务id */
    private Integer  id;

    /** 任务名称 */
    private String jobName;

    private String description;

    /** 任务执行类 */
    private String jobClass;

    /** 任务状态 启动还是暂停*/
    private Integer status;

    /** 任务运行时间表达式 */
    private String cronExpression;
    //省略getter setter

    private String createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
  }