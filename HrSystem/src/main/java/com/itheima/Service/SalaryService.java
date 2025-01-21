package com.itheima.Service;

import com.itheima.pojo.Salary;

import java.util.List;

public interface SalaryService {
    List<Salary> getSalariesByEmployeeId(Long employeeId); // 查询员工工资记录

    void insertSalary(Salary salary); // 添加工资记录
}
