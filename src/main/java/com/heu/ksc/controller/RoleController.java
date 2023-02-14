package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.heu.ksc.entity.Role;
import com.heu.ksc.service.RoleService;
import com.heu.ksc.service.impl.RoleServiceImpl;
import com.heu.ksc.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @RequestMapping("/roleList")
    @ResponseBody
    public String getRoleList() {
        List<Role> roles = roleService.selectRoleList();
        return JSON.toJSONString(AjaxResult.success("查询成功", roles));
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return JSON.toJSONString(AjaxResult.success("添加成功"));
    }

    @RequestMapping("byId")
    @ResponseBody
    public String selectById(Integer id) {
        Role role = roleService.selectById(id);
        return JSON.toJSONString(AjaxResult.success("查询成功",role));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String editRole(@RequestBody Role role) {
        roleService.editRole(role);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteRole(Integer id) {
        roleService.deleteRole(id);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

}
