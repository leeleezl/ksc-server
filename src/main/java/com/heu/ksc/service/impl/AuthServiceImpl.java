package com.heu.ksc.service.impl;

import com.heu.ksc.dao.AuthMapper;
import com.heu.ksc.entity.Auth;
import com.heu.ksc.entity.User;
import com.heu.ksc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> menus(User loginUser) {
        List<Auth> AuthList = authMapper.menus(loginUser);
        List<Auth> AuthTree = buildAuthTree(AuthList,0);
        return AuthTree;
    }

    @Override
    public List<Auth> menuList() {
        return authMapper.menuList();
    }

    @Override
    public List<Auth> selectMenusByRoleId(Integer roleId) {
        List<Auth> AuthList = authMapper.selectMenusByRoleId(roleId);
        List<Auth> Auths = buildAuthTree(AuthList, 0);
        return Auths;
    }

    @Override
    public void deleteById(Integer id) {
        authMapper.deleteById(id);
    }

    @Override
    public List<Auth> showAllAuthTree() {
        List<Auth> auths = authMapper.menuList();
        List<Auth> authTree = buildAuthTree(auths, 0);
        return authTree;
    }

    private List<Auth> buildAuthTree(List<Auth> AuthList, Integer pId) {
        List<Auth> treeList = new ArrayList<>();
        AuthList.forEach(Auth -> {
            if (pId.equals(Auth.getPId())) {
                Auth.setChildren(buildAuthTree(AuthList, Auth.getId()));
                treeList.add(Auth);
            }
        });
        return treeList;
    }
}

