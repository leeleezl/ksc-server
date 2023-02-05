package com.heu.ksc.service.impl;

import com.heu.ksc.dao.DepartmentMapper;
import com.heu.ksc.entity.Department;
import com.heu.ksc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> allDept() {
        List<Department> deptList = departmentMapper.allDept();
        List<Department> deptTree = buildDeptTree(deptList, 101);
        return deptTree;
    }

    @Override
    public List<Department> depts() {
        return departmentMapper.allDept();
    }

    private List<Department> buildDeptTree(List<Department> deptList, Integer pId) {
        List<Department> treeList = new ArrayList<>();
        deptList.forEach(dept -> {
            if (pId.equals(dept.getPId())) {
                dept.setChildren(buildDeptTree(deptList, dept.getId()));
                treeList.add(dept);
            }
        });
        return treeList;
    }
}
