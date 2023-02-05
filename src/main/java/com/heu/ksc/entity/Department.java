package com.heu.ksc.entity;

import lombok.Data;

import java.util.List;

@Data
public class Department extends BaseVo {

    private Integer id;

    private String deptName;

    private Integer pId;

    private String ancestors;

    private Integer level;

    private List<Department> children;

}
