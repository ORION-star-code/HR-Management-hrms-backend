package com.itheima.Service.Impl;

import com.itheima.Mapper.SalaryMapper;
import com.itheima.Service.SalaryService;
import com.itheima.pojo.Salary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public List<Salary> getSalariesByEmployeeId(Long employeeId) {
        // 查询薪资数据
        List<Salary> salaries = salaryMapper.getSalariesByEmployeeId(employeeId);

        // 调试日志
        if (salaries == null || salaries.isEmpty()) {
            log.warn("No salary records found for employeeId: {}", employeeId);
        } else {
            log.info("Found salaries: {}", salaries);
        }

        return salaries;
    }

    @Override
    public void insertSalary(Salary salary) {
        // 插入薪资记录
        salaryMapper.insertSalary(salary);
    }
}
