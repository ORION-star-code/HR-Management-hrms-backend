package com.itheima.Controller;

import com.itheima.Service.LeaveResignationApplicationService;
import com.itheima.pojo.LeaveRequest;
import com.itheima.pojo.ResignationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class LeaveResignationApplicationController {

    @Autowired
    private LeaveResignationApplicationService applicationService;

    // 提交请假申请
    @PostMapping("/leave")
    public String submitLeaveRequest(@RequestBody LeaveRequest leaveRequest, @RequestParam Long employeeId) {
        log.info("Received leave request for employeeId: {}", employeeId);
        leaveRequest.setEmployeeId(employeeId);
        leaveRequest.setStatus("pending");
        try {
            applicationService.saveLeaveRequest(leaveRequest);
            return "请假申请提交成功";
        } catch (Exception e) {
            log.error("Error submitting leave request: {}", e.getMessage());
            return "请假申请提交失败，请稍后重试";
        }
    }

    // 提交离职申请
    @PostMapping("/resignation")
    public String submitResignationRequest(@RequestBody ResignationRequest resignationRequest, @RequestParam Integer employeeId) {
        log.info("Received resignation request for employeeId: {}", employeeId);
        resignationRequest.setEmployeeId(employeeId);
        resignationRequest.setStatus("pending");
        try {
            applicationService.saveResignationRequest(resignationRequest);
            return "离职申请提交成功";
        } catch (Exception e) {
            log.error("Error submitting resignation request: {}", e.getMessage());
            return "离职申请提交失败，请稍后重试";
        }
    }
}
