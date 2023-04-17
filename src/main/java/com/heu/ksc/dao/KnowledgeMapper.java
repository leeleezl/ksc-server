package com.heu.ksc.dao;

import com.heu.ksc.entity.Knowledge;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface KnowledgeMapper {

   List<Knowledge> list(Knowledge knowledge);

   int insert(Knowledge knowledge);

   Knowledge selectById(Knowledge knowledge);

   int updateById(Knowledge knowledge);

   List<Knowledge> checkList(Knowledge knowledge);

   int edit(Knowledge knowledge);

   List<Knowledge> getMyKnowledgeList(Knowledge knowledge);

   int collect(Knowledge knowledge);

   int cancelCollect(Knowledge knowledge);

   List<Knowledge> myCollectList(@Param("queryKey") String queryKey, @Param("userId") Integer userId);

   int deleteKnowledge(@Param("id") Integer id);

   List<Knowledge> getMyMistakeKnowledge(Knowledge knowledge);

   int addCommentCount(@Param("knowledgeId") Integer knowledgeId);

   List<Knowledge> autoValidList(@Param("validTime")Date validTime);

   List<Knowledge> selectNew();

   List<Knowledge> autoInvalidList();
}
