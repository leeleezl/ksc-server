package com.heu.ksc.service;

import com.heu.ksc.entity.User;
import com.heu.ksc.entity.UserRole;

import java.util.List;

public interface UserService {

    void add(User user);

    User login(User user);

    void logout();

    List<User> users(User user);

    void updateStatus(User user);

    void edit(User user);

    void userRole(User user);

    User selectById(Integer id);

    void deleteUserById(Integer id);

    UserRole selectUserRoleByUsername(String username);


}
