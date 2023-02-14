package com.heu.ksc.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role extends BaseVo{

    private Integer id;

    private String code;

    private String name;

    private Boolean isDeleted;

    private String desc;

    private List<Auth> authList;

}
