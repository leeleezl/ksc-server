package com.heu.ksc.dao;

import com.heu.ksc.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {

    int insertUserRole(@Param("username") String username, @Param("roleId") Integer roleId);

    int updateUserRole(UserRole userRole);

    UserRole selectUserRoleByUsername(@Param("username") String username);

}
