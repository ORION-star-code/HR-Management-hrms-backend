package com.itheima.Service.Impl;

import com.itheima.Mapper.EmployeeMapper;
import com.itheima.Mapper.UserMapper;
import com.itheima.Service.EmployeeService;
import com.itheima.pojo.Employee;
import com.itheima.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.selectEmployeeAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeMapper.selectById(id);
    }

    @Override
    @Transactional
    public boolean addEmployee(Employee employee) {
        if (employee.getSalaryBase() == null) {
            employee.setSalaryBase(BigDecimal.valueOf(5000));  // 默认薪资基数为 0
        }

        // 1. 创建新用户
        User newUser = new User();
        newUser.setUsername(employee.getName());  // 假设员工名字是用户名
        newUser.setPassword("default_password");  // 你可以在此进行密码加密
        newUser.setRole("employee");  // 角色设为员工

        // 插入新用户并获取 user_id
        int userResult = userMapper.insert(newUser);
        if (userResult <= 0) {
            log.error("用户创建失败");
            return false;  // 用户创建失败，回滚事务
        }

        // 2. 获取新创建的 user_id
        int userId = newUser.getId();
        employee.setUserId(userId);  // 关联到员工的 userId

        // 3. 确保 salary_base（薪资基数）等字段正确传递到数据库
        log.info("新员工薪资基数: " + employee.getSalaryBase());

        // 4. 插入员工记录
        int employeeResult = employeeMapper.insert(employee);
        if (employeeResult > 0) {
            log.info("员工创建成功");
            return true;  // 员工插入成功
        } else {
            log.error("员工创建失败");
            return false;  // 员工创建失败，回滚事务
        }
    }

    @Override
    @Transactional
    public boolean updateEmployee(Employee employee) {
        // 确保 userId 不为空
        if (employee.getUserId() == null) {
            log.error("更新员工时，userId 为空！");
            return false; // 返回失败，避免进行错误的更新操作
        }

        // 如果薪资基数为空，设置默认值
        if (employee.getSalaryBase() == null) {
            employee.setSalaryBase(BigDecimal.valueOf(5000)); // 默认薪资基数为 5000
        }

        // 1. 更新用户信息
        User userToUpdate = new User();
        userToUpdate.setId(employee.getUserId()); // 设置要更新的用户ID
        userToUpdate.setUsername(employee.getName()); // 设置新的用户名
        // 在此添加更多字段的更新，如果需要的话

        int userUpdateResult = userMapper.update(userToUpdate);
        if (userUpdateResult <= 0) {
            log.error("更新用户信息失败");
            return false; // 如果更新用户失败，回滚事务
        }

        // 2. 更新员工信息
        int employeeUpdateResult = employeeMapper.update(employee);
        if (employeeUpdateResult > 0) {
            log.info("员工信息更新成功");
            return true; // 更新成功
        } else {
            log.error("更新员工信息失败");
            return false; // 更新失败
        }
    }
    @Override
    public boolean deleteEmployee(Long id) {
        return employeeMapper.softDelete(id) > 0;
    }

    @Override
    public List<Employee> searchEmployees(String keyword) {
        return employeeMapper.search(keyword);
    }
}

