package com.itheima.Service;

import com.itheima.pojo.Employee;

public interface EmpService {

    Employee getEmployeeById(Long id); // 查询员工信息

    void updateEmployee(Employee employee); // 更新员工信息

    void insertEmployee(Employee employee); // 添加员工

    void deleteEmployee(Long id); // 删除员工
}
