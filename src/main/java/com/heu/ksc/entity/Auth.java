package com.heu.ksc.entity;

import lombok.Data;

import java.util.List;

@Data
public class Auth extends BaseVo{

    private Integer id;

    private String name;

    private Integer pId;

    private String path;

    private String ancestors;

    private Integer level;

    //
    private List<Auth> children;

}
