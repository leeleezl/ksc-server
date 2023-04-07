package com.heu.ksc.util;

public interface KscConstant {

    //实体类型：知识
    int ENTITY_TYPE_KNOWLEDGE = 1;

    //实体类型：评论
    int ENTITY_TYPE_COMMENT = 2;

    //实体类型：人
    int ENTITY_TYPE_USER = 3;

    //启动
    int RUNNING = 1;
    //暂停
    int PAUSE = 2;
    //删除
    int DELETED = 0;

    //1-审核中  2-机器审核通过 3-人工审核通过   4-上架  0-下架  5-审核未通过
    int CHECKING = 1;
    int RO_PASS = 2;
    int PEO_PASS = 3;
    int VALID = 4;
    int INVALID = 0;
    int FAILED_PASS = 5;
}
