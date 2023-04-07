package com.heu.ksc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class Comment {

    private Integer id;

    private Integer userId;

    private Integer entityType;

    private Integer entityId;

    private Integer targetId;

    private String content;

    private boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<Comment> replyList;

    private String username;

    private String targetUser;

    private String deptName;

}
