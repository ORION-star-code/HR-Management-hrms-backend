package com.itheima.Controller;

import com.itheima.Service.AttendanceService;
import com.itheima.pojo.Attendance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // 获取员工考勤记录
    @GetMapping
    public List<Attendance> getAttendanceRecords(@RequestParam Integer employeeId) {
        log.info("Fetching attendance records for employeeId: {}", employeeId);
        return attendanceService.getAttendanceByEmployeeId(employeeId);
    }
}
