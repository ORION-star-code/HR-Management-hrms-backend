package com.itheima.Service;

import com.itheima.pojo.Attendance;

import java.math.BigDecimal;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendanceRecords(int offset, int limit); // 查询所有考勤记录

    boolean addAttendanceRecord(Attendance attendance); // 添加考勤记录

    boolean updateAttendanceRecord(Attendance attendance); // 更新考勤记录

    boolean deleteAttendanceRecord(Long id); // 删除考勤记录

    boolean penalizeEmployee(Long employeeId, BigDecimal penaltyAmount, String penaltyReason); // 对迟到/缺勤员工进行处罚

    List<Attendance> getAttendanceByEmployeeId(Integer employeeId);


}
