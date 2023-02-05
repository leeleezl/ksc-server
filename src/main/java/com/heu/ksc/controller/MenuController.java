package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.heu.ksc.entity.Menu;
import com.heu.ksc.entity.User;
import com.heu.ksc.service.impl.MenuServiceImpl;
import com.heu.ksc.util.AjaxResult;
import com.heu.ksc.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys")
@CrossOrigin
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @Autowired
    private TokenUtil tokenUtil;

    @RequestMapping("/menus")
    @ResponseBody
    public String menus() {
        List<Menu> menus = menuService.menus(tokenUtil.getLoginUser());
        return JSON.toJSONString(AjaxResult.success("查询成功", menus));
    }
    @RequestMapping("/menuList")
    @ResponseBody
    public String menuList() {
        List<Menu> menuList = menuService.menuList();
        return JSON.toJSONString(AjaxResult.success("查询成功", menuList));
    }

}
