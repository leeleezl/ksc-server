package com.heu.ksc.dao;

import com.heu.ksc.entity.Knowledge;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KnowledgeMapper {

   List<Knowledge> list(Knowledge knowledge);

   int insert(Knowledge knowledge);

   Knowledge selectById(Knowledge knowledge);

   int updateById(Knowledge knowledge);

   List<Knowledge> listCheck(Knowledge knowledge);

   int edit(Knowledge knowledge);
}
