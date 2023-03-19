package com.heu.ksc.entity;

import lombok.Data;

@Data
public class BaseVo {
    private Integer page;

    private Integer size;

    private String queryKey;

    private Integer deptId;

}
