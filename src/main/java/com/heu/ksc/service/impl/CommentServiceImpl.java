package com.heu.ksc.service.impl;

import com.heu.ksc.dao.CommentMapper;
import com.heu.ksc.dao.KnowledgeMapper;
import com.heu.ksc.entity.Comment;
import com.heu.ksc.service.CommentService;
import com.heu.ksc.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Autowired
    private UserServiceImpl userService;

    @Override
    @Transactional
    public void addComment(Integer knowLedgeId, Comment comment) {
        comment.setUserId(tokenUtil.getUserId());
        comment.setCreateTime(new Date());
        commentMapper.addComment(comment);
        //帖子评论数 ++
        knowledgeMapper.addCommentCount(knowLedgeId);
        //TODO 计算帖子得分
    }

    @Override
    public List<Comment> getCommentByEntity(int entityType, int entityId) {
        List<Comment> commentByEntity = commentMapper.getCommentByEntity(entityType, entityId);
        for (Comment comment : commentByEntity) {
            comment.setTargetUser(userService.selectById(comment.getTargetId()).getRealName());
        }
        return commentByEntity;
    }
}
