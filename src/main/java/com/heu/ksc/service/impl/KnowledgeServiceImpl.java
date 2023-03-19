package com.heu.ksc.service.impl;

import com.github.pagehelper.PageHelper;
import com.heu.ksc.dao.KnowledgeMapper;
import com.heu.ksc.dao.UserMapper;
import com.heu.ksc.entity.Knowledge;
import com.heu.ksc.service.KnowledgeService;
import com.heu.ksc.service.UserService;
import com.heu.ksc.util.RedisUtils;
import com.heu.ksc.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public List<Knowledge> getMyKnowledgeList(Knowledge knowledge) {
        Integer userId = tokenUtil.getUserId();
        knowledge.setUserId(userId);
        PageHelper.startPage(knowledge.getPage(), knowledge.getSize());
        List<Knowledge> myKnowledgeList = knowledgeMapper.getMyKnowledgeList(knowledge);
        return myKnowledgeList;
    }

    @Override
    @Transactional
    public void collect(Knowledge knowledge) {
        int isCollected = userMapper.isCollected(tokenUtil.getUserId(), knowledge.getId());
        if (isCollected == 0) {
            userMapper.collect(tokenUtil.getUserId(), knowledge.getId(), new Date());
            knowledgeMapper.collect(knowledge);
        } else {
            userMapper.cancelCollect(tokenUtil.getUserId(), knowledge.getId());
            knowledgeMapper.cancelCollect(knowledge);
        }
    }

    @Override
    @Transactional
    public void cancelCollect(Knowledge knowledge) {
        userMapper.cancelCollect(tokenUtil.getUserId(), knowledge.getId());
        knowledgeMapper.cancelCollect(knowledge);
    }

    @Override
    public List<Knowledge> myCollectList(Knowledge knowledge) {
        PageHelper.startPage(knowledge.getPage(), knowledge.getSize());
        List<Knowledge> myCollectList = knowledgeMapper.myCollectList(knowledge.getQueryKey(), tokenUtil.getUserId());
        return myCollectList;
    }

    @Override
    public void deleteKnowledge(Integer id) {
        knowledgeMapper.deleteKnowledge(id);
    }


}
