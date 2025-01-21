package com.itheima.Service.Impl;

import com.itheima.Mapper.AttendanceMapper;
import com.itheima.Service.AttendanceService;
import com.itheima.pojo.Attendance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public List<Attendance> getAllAttendanceRecords(int offset, int limit) {
        return attendanceMapper.selectAllAttendanceRecords(0,10);
    }

    @Override
    public boolean addAttendanceRecord(Attendance attendance) {
        // 使用 null 检查避免插入无效数据
        if (attendance == null || attendance.getEmployeeId() == null) {
            return false;
        }
        return attendanceMapper.insertAttendanceRecord(attendance) > 0;
    }

    @Override
    public boolean updateAttendanceRecord(Attendance attendance) {
        return attendanceMapper.updateAttendanceRecord(attendance) > 0;
    }

    @Override
    public boolean deleteAttendanceRecord(Long id) {
        return attendanceMapper.deleteAttendanceRecord(id) > 0;
    }

    @Override
    public boolean penalizeEmployee(Long employeeId, BigDecimal penaltyAmount, String penaltyReason) {
        if (employeeId == null || penaltyReason == null || penaltyReason.isEmpty()) {
            log.error("处罚信息不完整");
            return false;  // 数据不合法
        }
        return attendanceMapper.addPenalty(employeeId, penaltyAmount,penaltyReason) > 0;
    }

    @Override
    public List<Attendance> getAttendanceByEmployeeId(Integer employeeId) {
        log.info("Fetching attendance records from the database for employeeId: {}", employeeId);
        log.info("Attendance records fetched from the database: {}", attendanceMapper.findAttendanceByEmployeeId(employeeId));
        return attendanceMapper.findAttendanceByEmployeeId(employeeId);
    }
}
