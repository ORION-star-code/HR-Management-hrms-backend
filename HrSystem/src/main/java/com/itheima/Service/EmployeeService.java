package com.itheima.Service;

import com.itheima.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees(); // 查询所有员工

    Employee getEmployeeById(Long id); // 根据 ID 查询员工

    boolean addEmployee(Employee employee); // 添加员工

    boolean updateEmployee(Employee employee); // 更新员工信息

    boolean deleteEmployee(Long id); // 删除员工（软删除）

    List<Employee> searchEmployees(String keyword); // 模糊查询
}
