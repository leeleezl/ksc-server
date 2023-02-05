package com.heu.ksc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User extends BaseVo implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String realName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String contact;

    private Integer deptId;

    private Boolean state;

    //
    private String roleName;
    //
    private String deptName;

}
