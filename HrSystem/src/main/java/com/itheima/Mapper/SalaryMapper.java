package com.itheima.Mapper;

import com.itheima.pojo.Salary;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SalaryMapper {

    // 查询员工的薪资记录（按时间倒序）
    @Select("SELECT s.id, s.employee_id, s.salary_month, s.base_salary, s.full_attendance_bonus, s.performance_bonus, s.deductions, s.total_salary, s.remarks, s.created_at " +
            "FROM salaries s WHERE s.employee_id = #{employeeId} ORDER BY s.salary_month DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employeeId", column = "employee_id"),
            @Result(property = "baseSalary", column = "base_salary"),
            @Result(property = "performanceBonus", column = "performance_bonus"),
            @Result(property = "attendanceBonus", column = "full_attendance_bonus"),
            @Result(property = "paymentDate", column = "salary_month"),
            @Result(property = "totalSalary", column = "total_salary"),
            @Result(property = "remarks", column = "remarks"),
            @Result(property = "salaryMonth", column = "salary_month", javaType = LocalDate.class)  // 映射为 LocalDate
    })
    List<Salary> getSalariesByEmployeeId(@Param("employeeId") Long employeeId);

    // 插入薪资记录
    @Insert("INSERT INTO salaries (employee_id, salary_month, base_salary, full_attendance_bonus, performance_bonus, deductions, total_salary, remarks) " +
            "VALUES (#{employeeId}, #{salaryMonth}, #{baseSalary}, #{fullAttendanceBonus}, #{performanceBonus}, #{deductions}, #{totalSalary}, #{remarks})")
    int insertSalary(Salary salary);

    // 批量插入薪资记录
    @Insert("<script>" +
            "INSERT INTO salaries (employee_id, salary_month, base_salary, full_attendance_bonus, performance_bonus, deductions, total_salary, remarks) VALUES " +
            "<foreach collection='salaries' item='salary' separator=','>" +
            "(#{salary.employeeId}, #{salary.salaryMonth}, #{salary.baseSalary}, #{salary.fullAttendanceBonus}, #{salary.performanceBonus}, #{salary.deductions}, #{salary.totalSalary}, #{salary.remarks})" +
            "</foreach>" +
            "</script>")
    int batchInsertSalaries(@Param("salaries") List<Salary> salaries);
}
