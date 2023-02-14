package com.heu.ksc.service;

import com.heu.ksc.entity.Auth;
import com.heu.ksc.entity.User;

import java.util.List;

public interface AuthService {

    public List<Auth> menus(User loginUser);

    List<Auth> menuList();

    List<Auth> selectMenusByRoleId(Integer roleId);

    void deleteById(Integer id);

    List<Auth> showAllAuthTree();
}
