package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.heu.ksc.entity.Auth;
import com.heu.ksc.service.impl.AuthServiceImpl;
import com.heu.ksc.util.AjaxResult;
import com.heu.ksc.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys")
@CrossOrigin
@Slf4j
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private TokenUtil tokenUtil;

    @RequestMapping("/menus")
    @ResponseBody
    public String menus() {
        List<Auth> menus = authService.menus(tokenUtil.getLoginUser());
        return JSON.toJSONString(AjaxResult.success("查询成功", menus));
    }
    @RequestMapping("/menuList")
    @ResponseBody
    public String menuList() {
        List<Auth> menuList = authService.menuList();
        return JSON.toJSONString(AjaxResult.success("查询成功", menuList));
    }

    @RequestMapping("/deleteAuth")
    @ResponseBody
    public String deleteById(Integer id) {
        authService.deleteById(id);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

    @RequestMapping("/getAuthTree")
    @ResponseBody
    public String getAuthTree() {
        List<Auth> authTree = authService.showAllAuthTree();
        return JSON.toJSONString(AjaxResult.success("查询成功",authTree));
    }

    @RequestMapping("/getThreeLevelAuth")
    @ResponseBody
    public String getThreeLevelAuth(Integer roleId) {
        List<Integer> defKeys = authService.getThreeLevelAuth(roleId);
        return JSON.toJSONString(AjaxResult.success("查询成功",defKeys));
    }

    @RequestMapping("/setAuth")
    @ResponseBody
    public String setAuth(@RequestBody Map authMap) {
        authService.setAuth(authMap);
        return JSON.toJSONString(AjaxResult.success("分配成功"));
    }

}
