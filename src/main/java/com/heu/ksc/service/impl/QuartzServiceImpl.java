package com.heu.ksc.service.impl;

import com.heu.ksc.dao.QuartzBeanMapper;
import com.heu.ksc.entity.QuartzBean;
import com.heu.ksc.entity.User;
import com.heu.ksc.service.QuartzService;
import com.heu.ksc.util.KscConstant;
import com.heu.ksc.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzBeanMapper quartzBeanMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    @Transactional
    public List<QuartzBean> selectAll(QuartzBean quartzBean) {
        return quartzBeanMapper.selectAll(quartzBean);
    }

    @Override
    @Transactional
    public void updateJob(QuartzBean quartzBean) {
        User loginUser = tokenUtil.getLoginUser();
        quartzBean.setUpdateUser(loginUser.getUsername());
        quartzBean.setUpdateTime(new Date());
        quartzBeanMapper.updateJob(quartzBean);
    }

    @Override
    @Transactional
    public void insertJob(QuartzBean quartzBean) {
//        User loginUser = tokenUtil.getLoginUser();
//        quartzBean.setCreateUser(loginUser.getUsername());
        quartzBean.setCreateUser("aaaaaaa");
        quartzBean.setCreateTime(new Date());
        quartzBean.setStatus(KscConstant.RUNNING);
        quartzBeanMapper.createJob(quartzBean);
    }
}
