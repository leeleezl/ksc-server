package com.heu.ksc.service.impl;

import com.heu.ksc.dao.RoleMapper;
import com.heu.ksc.dao.UserRoleMapper;
import com.heu.ksc.entity.Auth;
import com.heu.ksc.entity.Role;
import com.heu.ksc.entity.UserRole;
import com.heu.ksc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> selectRoleList() {
        List<Role> roleList = roleMapper.selectRoleList();
        for (Role role : roleList) {
            List<Auth> auths = authService.selectMenusByRoleId(role.getId());
            role.setAuthList(auths);
        }
        return roleList;
    }

    @Override
    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    @Override
    public Role selectById(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public void editRole(Role role) {
        roleMapper.editRole(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        userRoleMapper.updateUserRole(userRole);
    }
}
