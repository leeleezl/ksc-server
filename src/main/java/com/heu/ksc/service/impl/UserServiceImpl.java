package com.heu.ksc.service.impl;

import com.github.pagehelper.PageHelper;
import com.heu.ksc.dao.UserMapper;
import com.heu.ksc.dao.UserRoleMapper;
import com.heu.ksc.entity.User;
import com.heu.ksc.entity.UserRole;
import com.heu.ksc.service.UserService;
import com.heu.ksc.util.Md5Util;
import com.heu.ksc.util.RedisUtils;
import com.heu.ksc.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    private TokenUtil tokenUtil;

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(Md5Util.EncoderByMd5(user.getPassword()));
        user.setCreateTime(new Date());
        user.setState(false);
        log.info(user.getUsername());
        userMapper.insert(user);
        userRoleMapper.insertUserRole(user.getUsername(), 103);
    }

    @Override
    public User login(User user) {
        user.setPassword(Md5Util.EncoderByMd5(user.getPassword()));
        return userMapper.login(user);
    }

    @Override
    public void logout() {
        redisUtil.remove(tokenUtil.getToken());
    }

    @Override
    public List<User> users(User user) {
        PageHelper.startPage(user.getPage(), user.getSize());
        return userMapper.users(user);
    }

    @Override
    public void updateStatus(User user) {
        userMapper.updateStatus(user);
    }

    @Override
    public void edit(User user) {
        userMapper.edit(user);
    }

    @Override
    public void userRole(User user) {
        userMapper.userRole(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public UserRole selectUserRoleByUsername(String username) {
        UserRole userRole = userRoleMapper.selectUserRoleByUsername(username);
        return userRole;
    }
}
