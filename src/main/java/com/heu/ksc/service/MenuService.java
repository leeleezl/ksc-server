package com.heu.ksc.service;

import com.heu.ksc.entity.Menu;
import com.heu.ksc.entity.User;

import java.util.List;

public interface MenuService {

    public List<Menu> menus(User loginUser);

    List<Menu> menuList();

}
