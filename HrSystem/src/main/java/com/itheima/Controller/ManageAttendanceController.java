package com.itheima.Controller;

import com.itheima.Service.AttendanceService;
import com.itheima.pojo.Attendance;
import com.itheima.pojo.Penalty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/attendance")
@CrossOrigin(origins = "*")
public class ManageAttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllAttendanceRecords(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        List<Attendance> records = attendanceService.getAllAttendanceRecords(offset, limit);
        Map<String, Object> response = new HashMap<>();
        response.put("records", records);
        response.put("totalCount", records.size());  // 你可以根据实际情况获取总数
        return ResponseEntity.ok(response);
    }

    // 添加考勤记录
    @PostMapping("/add")
    public ResponseEntity<String> addAttendanceRecord(@RequestBody Attendance attendance) {
        attendanceService.addAttendanceRecord(attendance);
        return ResponseEntity.ok("考勤记录添加成功");
    }

    // 修改考勤记录
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editAttendanceRecord(@PathVariable Long id, @RequestBody Attendance attendance) {
        attendanceService.updateAttendanceRecord(attendance);
        return ResponseEntity.ok("考勤记录更新成功");
    }

    @PostMapping("/penalty/{id}")
    public ResponseEntity<String> penalizeEmployee(@PathVariable Long id, @RequestBody Penalty penalty) {
        try {
            // 调用service方法，直接使用传递的员工ID和处罚信息
            boolean result = attendanceService.penalizeEmployee(id, penalty.getPenaltyAmount(), penalty.getPenaltyReason());

            // 判断是否成功
            if (result) {
                return ResponseEntity.ok("处罚成功");
            } else {
                return ResponseEntity.status(500).body("处罚失败");
            }
        } catch (Exception e) {
            log.error("处罚失败", e);
            return ResponseEntity.status(500).body("处罚失败，请稍后重试");
        }
    }
}
