package com.heu.ksc.dao;

import com.heu.ksc.entity.Mistake;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MistakeMapper {

    int addMistake(Mistake mistake);



}
