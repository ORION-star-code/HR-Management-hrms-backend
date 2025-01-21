package com.itheima.Service.Impl;

import com.itheima.Mapper.EmpMapper;
import com.itheima.Service.EmpService;
import com.itheima.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmpImpl implements EmpService {
    @Autowired
    private EmpMapper employeeMapper;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void insertEmployee(Employee employee) {
        employeeMapper.insertEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeMapper.deleteEmployee(id);
    }
}
