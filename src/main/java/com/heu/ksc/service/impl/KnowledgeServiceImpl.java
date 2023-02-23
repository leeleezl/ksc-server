package com.heu.ksc.service.impl;

import com.github.pagehelper.PageHelper;
import com.heu.ksc.dao.KnowledgeMapper;
import com.heu.ksc.entity.Knowledge;
import com.heu.ksc.service.KnowledgeService;
import com.heu.ksc.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public List<Knowledge> list(Knowledge knowledge) {
        PageHelper.startPage(knowledge.getPage(), knowledge.getSize());
        return knowledgeMapper.list(knowledge);

    }

    @Override
    public void add(Knowledge knowledge) {
        knowledge.setStatus(1);   //审核中
        knowledge.setUserId(tokenUtil.getUserId());
        knowledgeMapper.insert(knowledge);
    }

    @Override
    public Knowledge selectById(Knowledge knowledge) {
        return knowledgeMapper.selectById(knowledge);
    }

    @Override
    public void updateById(Knowledge knowledge) {
        knowledgeMapper.updateById(knowledge);
    }

    @Override
    public List<Knowledge> checkList(Knowledge knowledge) {
        PageHelper.startPage(knowledge.getPage(), knowledge.getSize());
        return knowledgeMapper.checkList(knowledge);

    }

    @Override
    public void edit(Knowledge knowledge) {
        knowledgeMapper.edit(knowledge);
    }

}
