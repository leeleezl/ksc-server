package com.heu.ksc.controller;

import com.alibaba.fastjson.JSON;
import com.heu.ksc.entity.Department;
import com.heu.ksc.service.DepartmentService;
import com.heu.ksc.service.impl.DepartmentServiceImpl;
import com.heu.ksc.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @RequestMapping("/allDept")
    @ResponseBody
    public String allDept() {
        List<Department> depts = departmentService.allDept();
        return JSON.toJSONString(AjaxResult.success("查询成功", depts));
    }

    @RequestMapping("/depts")
    @ResponseBody
    public String depts() {
        List<Department> depts = departmentService.depts();
        return JSON.toJSONString(AjaxResult.success("查询成功", depts));
    }

}
