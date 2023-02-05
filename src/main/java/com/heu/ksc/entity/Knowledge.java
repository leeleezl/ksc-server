package com.heu.ksc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
public class Knowledge extends BaseVo implements Serializable {

    private Integer id;

    private Integer userId;

    private String k_name;

    private String abstractInfo;

    private String content;

    private Integer type;

    private Integer status;    //0-审核中  1-审核通过  2-审核未通过

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date validTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidTime;

    private Integer commentCount;

    private Integer collectCount;

    private String keywords;

    private Double score;

}
