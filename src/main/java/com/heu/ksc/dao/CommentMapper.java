package com.heu.ksc.dao;

import com.heu.ksc.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    int addComment(Comment comment);

    List<Comment> getCommentByEntity(@Param("entityType") int entityType, @Param("entityId") int entityId);

}
