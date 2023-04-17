package com.heu.ksc.entity;

import lombok.Data;

@Data
public class Mistake extends BaseVo {

    private Integer id;

    private String content;

    private Integer uId;

    private Integer kId;

}
