package com.heu.ksc.dao;

import com.heu.ksc.entity.Menu;
import com.heu.ksc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    public List<Menu> menus(User loginUser);

    List<Menu> menuList();

}
