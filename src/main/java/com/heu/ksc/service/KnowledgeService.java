package com.heu.ksc.service;

import com.heu.ksc.entity.Knowledge;

import java.util.List;

public interface KnowledgeService {

    List<Knowledge> list(Knowledge knowledge);

    void add(Knowledge knowledge);

    Knowledge selectById(Knowledge knowledge);

    void updateById(Knowledge knowledge);

    List<Knowledge> listCheck(Knowledge knowledge);

    void edit(Knowledge knowledge);

}
