package com.itheima.Controller;

import com.itheima.Service.LeaveService;
import com.itheima.pojo.LeaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/leave")
@CrossOrigin(origins = "*")
public class ManageLeaveController {

    @Autowired
    private LeaveService leaveService;

    // 获取所有请假申请记录，并支持分页和过滤条件
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllLeaveRequests(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status) {

        int offset = (page - 1) * size;

        Map<String, Object> response = leaveService.getRequestsByCondition(page, size, name, type, status);

        // 打印查询结果
        System.out.println("返回的数据: " + response);

        List<LeaveRequest> leaveRequests = (List<LeaveRequest>) response.get("records");
        System.out.println("查询返回的请假记录: " + leaveRequests);
        int totalCount = (int) response.get("totalCount");

        return ResponseEntity.ok(Map.of(
                "data", leaveRequests,
                "totalCount", totalCount,
                "currentPage", page,
                "totalPages", (int) Math.ceil((double) totalCount / size)
        ));
    }

    // 审批请假申请
    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approveLeaveRequest(@PathVariable Long id, @RequestBody Map<String, String> status) {
        String leaveStatus = status.get("status");
        String leaveType = status.get("type"); // 获取 leaveType
        boolean result = leaveService.updateRequestStatus(id, leaveStatus, leaveType); // 更新状态和类型
        if (result) {
            return ResponseEntity.ok("请假申请已处理");
        }
        return ResponseEntity.status(400).body("操作失败");
    }

    // 处理离职申请（软删除员工）
    @PutMapping("/reject/{id}")
    public ResponseEntity<String> rejectLeaveRequest(@PathVariable Long id, @RequestBody Map<String, String> status) {
        String leaveStatus = status.get("status");
        String leaveType = status.get("type"); // 获取 leaveType
        boolean result = leaveService.updateRequestStatus(id, leaveStatus, leaveType); // 更新状态和类型
        if (result) {
            return ResponseEntity.ok("请假申请已拒绝");
        }
        return ResponseEntity.status(400).body("操作失败");
    }
}
