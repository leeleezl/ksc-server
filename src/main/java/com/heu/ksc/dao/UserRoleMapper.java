package com.heu.ksc.dao;

import com.heu.ksc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {

    int insertUserRole(@Param("username") String username, @Param("roleId") Integer roleId);

}
