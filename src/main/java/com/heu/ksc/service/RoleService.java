package com.heu.ksc.service;

import com.heu.ksc.entity.Role;
import com.heu.ksc.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface RoleService {

    List<Role> selectRoleList();

    void addRole(Role role);

    Role selectById(Integer id);

    void editRole(Role role);

    void deleteRole(Integer id);

    void updateUserRole(UserRole userRole);

}
