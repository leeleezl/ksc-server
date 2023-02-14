package com.heu.ksc.dao;

import com.heu.ksc.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> selectRoleList();

    int addRole(Role role);

    Role selectById(@Param("id") Integer id);

    int editRole(Role role);

    int deleteRole(@Param("id") Integer id);

}
