package com.itheima.Controller;

import com.itheima.Service.SalaryService;
import com.itheima.pojo.Salary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee/salary")
@CrossOrigin(origins = "*")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<Salary>> getEmployeeSalaries(@RequestParam Long employeeId) {
        log.info("Received employeeId: {}", employeeId);  // 打印 employeeId 用于调试

        // 获取薪资记录
        List<Salary> salaries = salaryService.getSalariesByEmployeeId(employeeId);

        // 如果未找到薪资记录，则返回提示信息
        if (salaries == null || salaries.isEmpty()) {
            log.warn("No salary records found for employeeId: {}", employeeId);
            return ResponseEntity.status(404).body(null);  // 返回 404 状态码表示未找到记录
        }

        return ResponseEntity.ok(salaries);
    }
}
