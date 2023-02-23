package com.heu.ksc.dao;

import com.heu.ksc.entity.Auth;
import com.heu.ksc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthMapper {

    List<Auth> menus(User loginUser);

    List<Auth> menuList();

    List<Auth> selectMenusByRoleId(@Param("roleId") Integer roleId);

    int deleteById(@Param("id") Integer id);

    List<Auth> getThreeLevelAuth(@Param("roleId") Integer roleId);

    int setAuth(@Param("roleId") Integer roleId, @Param("authIds") List<Integer> authIds);
}
