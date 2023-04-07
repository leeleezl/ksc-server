package com.heu.ksc.service;

import com.heu.ksc.entity.QuartzBean;

import java.util.List;

public interface QuartzService {

    List<QuartzBean> selectAll(QuartzBean quartzBean);

    void updateJob(QuartzBean quartzBean);

    void insertJob(QuartzBean quartzBean);

}
