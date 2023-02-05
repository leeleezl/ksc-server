package com.heu.ksc.dao;

import com.heu.ksc.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    List<Department> allDept();

}
