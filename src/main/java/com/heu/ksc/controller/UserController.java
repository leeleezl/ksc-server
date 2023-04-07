package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.heu.ksc.entity.User;
import com.heu.ksc.entity.UserRole;
import com.heu.ksc.service.RoleService;
import com.heu.ksc.service.impl.RoleServiceImpl;
import com.heu.ksc.service.impl.UserServiceImpl;
import com.heu.ksc.util.AjaxResult;
import com.heu.ksc.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private RedisUtils redisUtil;

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        userService.add(user);
        return JSON.toJSONString(AjaxResult.success("添加成功"));
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user) {
        User loginUser = userService.login(user);
        AjaxResult ajaxResult = null;

        if(loginUser != null) {
            //生成token
            String token = UUID.randomUUID().toString();
            //把token放入redis中
            redisUtil.set(token, loginUser, 60L, TimeUnit.MINUTES);
//            Map dataMap = new HashMap();
//            dataMap.put("loginUserName", loginUser.getUsername());
            ajaxResult = new AjaxResult(true, token, loginUser);
        } else {
            ajaxResult = new AjaxResult(false, "账号或密码错误");
        }
        return JSON.toJSONString(ajaxResult);
    }

    /**
     * 退出系统
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        userService.logout();
        return JSON.toJSONString(AjaxResult.success("成功退出"));
    }

    /**
     * 用户列表
     * @param user
     * @return
     */
    @RequestMapping("/users")
    @ResponseBody
    public String Users(User user) {
        //查询用户列表
        List<User> users = userService.users(user);
        //封装到pageInfo
        PageInfo<User> usersList = new PageInfo<>(users);
        return JSON.toJSONString(AjaxResult.success("查询成功", usersList));
    }

    @RequestMapping("/status")
    @ResponseBody
    public String updateStatus(@RequestBody User user) {
        userService.updateStatus(user);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody User user) {
        userService.edit(user);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/userRole")
    @ResponseBody
    public String userRole(@RequestBody User user) {
        userService.userRole(user);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/byId")
    @ResponseBody
    public String selectById(Integer id) {
        User user = userService.selectById(id);
        user.setPassword("");
        return JSON.toJSONString(AjaxResult.success("查询成功", user));
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteUserById(Integer id) {
        userService.deleteUserById(id);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

    @RequestMapping("/updateUserRole")
    @ResponseBody
    public String updateUserRole(@RequestBody UserRole userRole) {
        roleService.updateUserRole(userRole);
        return JSON.toJSONString(AjaxResult.success("分配成功"));
    }

    @RequestMapping("/selectUserRoleByUsername")
    @ResponseBody
    public String selectUserRoleByUsername(String username) {
        UserRole userRole = userService.selectUserRoleByUsername(username);
        return JSON.toJSONString(AjaxResult.success("查询成功",userRole));
    }
}
