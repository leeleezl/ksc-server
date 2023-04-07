package com.heu.ksc.service;

import com.heu.ksc.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Integer knowledgeId, Comment comment);

    List<Comment> getCommentByEntity(int entityType, int entityId);

}
