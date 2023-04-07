package com.heu.ksc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Knowledge extends BaseVo implements Serializable {

    private Integer id;

    private Integer userId;

    private String kName;

    private String abstractInfo;

    private String content;

    private Integer type;

    private Integer status;    //1-审核中  2-机器审核通过 3-人工审核通过 0-审核未通过  4-有错误

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

    private String errInfo;

    private String checkedName;

    private String username;  //作者

    private List<Comment> commentList;

    public String getkName() {
        return kName;
    }
}
