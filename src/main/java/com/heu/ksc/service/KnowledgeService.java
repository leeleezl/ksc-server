package com.heu.ksc.service;

import com.heu.ksc.entity.Knowledge;

import java.beans.IntrospectionException;
import java.util.List;

public interface KnowledgeService {

    List<Knowledge> list(Knowledge knowledge);

    void add(Knowledge knowledge);

    Knowledge selectById(Knowledge knowledge);

    void updateById(Knowledge knowledge);

    List<Knowledge> checkList(Knowledge knowledge);

    void edit(Knowledge knowledge);

    List<Knowledge> getMyKnowledgeList(Knowledge knowledge);

    void collect(Knowledge knowledge);

    void cancelCollect(Knowledge knowledge);

    List<Knowledge> myCollectList(Knowledge knowledge);

    void deleteKnowledge(Integer id);

    List<Knowledge> getMyMistakeKnowledge(Knowledge knowledge);

    void addCommentCount(Integer knowledgeId);
}
