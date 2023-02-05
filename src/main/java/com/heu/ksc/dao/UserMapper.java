package com.heu.ksc.dao;

import com.heu.ksc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(User user);

    User login(User user);

    List<User> users(User user);

    int updateStatus(User user);

    int edit(User user);

    int userRole(User user);

    User selectById(@Param("id") Integer id);

    int deleteUserById(@Param("id") Integer id);

    int insertUserRole(Integer userId, Integer roleId);

    User selectByUsername(String username);
}
