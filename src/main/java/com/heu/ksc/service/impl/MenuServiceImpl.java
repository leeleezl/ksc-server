package com.heu.ksc.service.impl;

import com.heu.ksc.dao.MenuMapper;
import com.heu.ksc.entity.Menu;
import com.heu.ksc.entity.User;
import com.heu.ksc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> menus(User loginUser) {
        List<Menu> menuList = menuMapper.menus(loginUser);
        List<Menu> menuTree = buildMenuTree(menuList,0);
        return menuTree;
    }

    @Override
    public List<Menu> menuList() {
        return menuMapper.menuList();
    }

    private List<Menu> buildMenuTree(List<Menu> menuList, Integer pId) {
        List<Menu> treeList = new ArrayList<>();
        menuList.forEach(menu -> {
            if (pId.equals(menu.getPId())) {
                menu.setChildren(buildMenuTree(menuList, menu.getId()));
                treeList.add(menu);
            }
        });
        return treeList;
    }
}
