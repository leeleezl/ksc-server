package com.heu.ksc.dao;

import com.heu.ksc.entity.QuartzBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuartzBeanMapper {

    List<QuartzBean> selectAll(QuartzBean quartzBean);

    int updateJob(QuartzBean quartzBean);

    int createJob(QuartzBean quartzBean);
}
