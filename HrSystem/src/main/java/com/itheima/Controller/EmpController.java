package com.itheima.Controller;

import com.itheima.Service.EmpService;
import com.itheima.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmpController {
    @Autowired
    private EmpService employeeService;

//    @GetMapping("/info")
//    public ResponseEntity<Employee> getEmployeeInfo(@RequestParam Long employeeId) {
//        Employee employee = employeeService.getEmployeeById(employeeId);
//        log.info("Employee info: " + employee);
//        return ResponseEntity.ok(employee);
//    }
    @GetMapping("/info")
    public ResponseEntity<Employee> getEmployeeInfo(@RequestParam Long employeeId) {
        log.info("Received employeeId: {}", employeeId);  // 打印 employeeId
        Employee employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployeeInfo(@RequestBody Employee employee) {
        log.info("Received Employee Info: {}", employee);  // 输出员工信息到日志
        try {
            employeeService.updateEmployee(employee);  // 调用更新方法
            return ResponseEntity.ok("Employee information updated successfully.");
        } catch (Exception e) {
            log.error("Error updating employee information", e);  // 打印错误日志
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee information.");
        }
    }
}
