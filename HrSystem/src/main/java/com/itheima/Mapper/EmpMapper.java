package com.itheima.Mapper;

import com.itheima.pojo.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmpMapper {

    // 查询员工信息
    @Select("SELECT * FROM employees WHERE id = #{id}")
    Employee getEmployeeById(Long id);

    // 更新员工信息
    @Update("UPDATE employees SET name = #{name}, avatar = #{avatar}, position = #{position}, email = #{email}, phone = #{phone}, department = #{department} WHERE id = #{id}")
    void updateEmployee(Employee employee);

    // 插入新员工
    @Insert("INSERT INTO employees (username, password, name, avatar, position, email, phone, department) VALUES (#{username}, #{password}, #{name}, #{avatar}, #{position}, #{email}, #{phone}, #{department})")
    void insertEmployee(Employee employee);

    // 删除员工信息
    @Delete("DELETE FROM employees WHERE id = #{id}")
    void deleteEmployee(Long id);
}
