package com.itheima.Mapper;

import com.itheima.pojo.Attendance;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AttendanceMapper {


    @Select("SELECT ar.id, ar.employee_id AS employeeId, ar.date, ar.status, ar.penalty, ar.notes, ar.created_at AS createdAt, e.name AS employeeName " +
            "FROM attendance_records ar " +
            "LEFT JOIN employees e ON ar.employee_id = e.id " +
            "LIMIT #{offset}, #{limit}")
    List<Attendance> selectAllAttendanceRecords(@Param("offset") int offset, @Param("limit") int limit);

    // 优化条件查询：使用动态 SQL
    @Select("<script>" +
            "SELECT * FROM attendance " +
            "WHERE 1 = 1 " +
            "<if test='employeeId != null'>AND employee_id = #{employeeId}</if> " +
            "<if test='status != null'>AND status = #{status}</if> " +
            "<if test='startDate != null'>AND date &gt;= #{startDate}</if> " +
            "<if test='endDate != null'>AND date &lt;= #{endDate}</if> " +
            "</script>")
    List<Attendance> selectByCondition(@Param("employeeId") Long employeeId, @Param("status") String status,
                                       @Param("startDate") String startDate, @Param("endDate") String endDate);


    // 添加考勤记录
    @Insert("INSERT INTO attendance_records (employee_id, date, status, remarks) " +
            "VALUES (#{employeeId}, #{date}, #{status}, #{remarks})")
    int insertAttendanceRecord(Attendance attendance);

    // 更新考勤记录（部分字段）
    @Update("<script>" +
            "UPDATE attendance SET " +
            "<if test='status != null'>status = #{status},</if> " +
            "<if test='remarks != null'>remarks = #{remarks},</if> " +
            "date = date " + // 保证最后没有多余的逗号
            "WHERE id = #{id}" +
            "</script>")
    int updateAttendanceRecord(Attendance attendance);

    // 删除考勤记录（物理删除）
    @Delete("DELETE FROM attendance_records WHERE id = #{id}")
    int deleteAttendanceRecord(@Param("id") Long id);

    // 在 AttendanceMapper 接口中定义 addPenalty 方法
    @Insert("INSERT INTO penalties (employee_id, penalty_amount, penalty_reason) VALUES (#{employeeId}, #{penaltyAmount}, #{penaltyReason})")
    int addPenalty(@Param("employeeId") Long employeeId, @Param("penaltyAmount") BigDecimal penaltyAmount, @Param("penaltyReason") String penaltyReason);


    // 根据员工ID查询考勤记录
    @Select("SELECT id, employee_id AS employeeId, date, status, penalty, notes, created_at AS createdAt " +
            "FROM attendance_records " +
            "WHERE employee_id = #{employeeId} ORDER BY date DESC")
    List<Attendance> findAttendanceByEmployeeId(Integer employeeId);
}
