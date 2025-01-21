package com.itheima.Mapper;


import com.itheima.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {


    // 查询所有员工（未被软删除的员工）
    @Select("SELECT * FROM employees WHERE deleted_at IS NULL")
    List<Employee> selectEmployeeAll();

    // 分页查询所有员工
    @Select("SELECT * FROM employees WHERE deleted_at IS NULL LIMIT #{offset}, #{limit}")
    List<Employee> selectPaginatedEmployees(@Param("offset") int offset, @Param("limit") int limit);

    // 根据用户 ID 查询员工信息
    @Select("SELECT * FROM employees WHERE user_id = #{userId} AND deleted_at IS NULL")
    Employee selectById(@Param("userId") Long userId);

    // 模糊查询员工（支持按姓名、部门、职位）
    @Select("<script>" +
            "SELECT * FROM employees WHERE deleted_at IS NULL " +
            "<if test='keyword != null'>AND (name LIKE CONCAT('%', #{keyword}, '%') " +
            "OR department LIKE CONCAT('%', #{keyword}, '%') " +
            "OR position LIKE CONCAT('%', #{keyword}, '%'))</if>" +
            "</script>")
    List<Employee> search(@Param("keyword") String keyword);

    // 插入员工数据
    @Insert("INSERT INTO employees (user_id, name, gender, phone, email, position, department, " +
            "emergency_contact, hire_date, salary_base, performance_bonus, full_attendance_bonus, status) " +
            "VALUES (#{userId}, #{name}, #{gender}, #{phone}, #{email}, #{position}, #{department}, " +
            "#{emergencyContact}, #{hireDate}, #{salaryBase}, #{performanceBonus}, #{fullAttendanceBonus}, #{status})")
    int insert(Employee employee);

    // 更新员工信息
    @Update("UPDATE employees SET name = #{name}, gender = #{gender}, phone = #{phone}, email = #{email}, " +
            "position = #{position}, department = #{department}, emergency_contact = #{emergencyContact}, " +
            "hire_date = #{hireDate}, salary_base = #{salaryBase}, performance_bonus = #{performanceBonus}, " +
            "full_attendance_bonus = #{fullAttendanceBonus}, status = #{status}, avatar = #{avatar} WHERE id = #{id} AND deleted_at IS NULL")
    int update(Employee employee);

    // 软删除员工
    @Update("UPDATE employees SET deleted_at = NOW() WHERE id = #{id}")
    int softDelete(@Param("id") Long id);

    // 获取员工信息（包括已删除的员工）
    @Select("SELECT * FROM employees WHERE id = #{id}")
    Employee getEmployeeById(@Param("id") Long id);

    // 更新员工部分字段（示例：仅更新姓名、头像和职位）
    @Update("UPDATE employees SET name = #{name}, avatar = #{avatar}, position = #{position} WHERE id = #{id} AND deleted_at IS NULL")
    int updateEmployeePartial(@Param("name") String name,
                              @Param("avatar") String avatar,
                              @Param("position") String position,
                              @Param("id") Long id);


    // 更新员工头像
    @Update("UPDATE employees SET avatar = #{avatar} WHERE id = #{id} AND deleted_at IS NULL")
    int updateAvatar(@Param("id") int id, @Param("avatar") String avatar);

    // 更新员工头像的URL
    @Update("UPDATE employees SET avatar_url = #{avatarUrl} WHERE id = #{id} AND deleted_at IS NULL")
    int updateAvatarUrl(@Param("id") int id, @Param("avatarUrl") String avatarUrl);

    // 删除员工头像
    @Update("UPDATE employees SET avatar = NULL WHERE id = #{id} AND deleted_at IS NULL")

    int deleteAvatar(@Param("id") int id);

    // 获取员工头像URL
    @Select("SELECT avatar_url FROM employees WHERE id = #{id}")
    String getAvatarUrlById(@Param("id") int id);
    @Select("SELECT id FROM employees WHERE user_id = #{userId}")
    Integer getEmployeeIdByUserId(@Param("userId") Integer userId);
}
