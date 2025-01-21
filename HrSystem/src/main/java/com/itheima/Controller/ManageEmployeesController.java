package com.itheima.Controller;

import com.itheima.Service.EmployeeService;
import com.itheima.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/employees")
@CrossOrigin(origins = "http://localhost:8081")
public class ManageEmployeesController {
    @Autowired
    private EmployeeService employeeService;

    // 查询所有员工信息
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // 按条件查询员工信息
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam(required = false) String keyword) {
        List<Employee> employees = employeeService.searchEmployees(keyword);
        return ResponseEntity.ok(employees);
    }

    // 新增员工
    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        log.info("接收到员工信息: " + employee);
        boolean result = employeeService.addEmployee(employee);
        if (result) {
            return ResponseEntity.ok("员工添加成功");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("员工添加失败");
        }
    }

    // 编辑员工信息
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editEmployee(@PathVariable Long id, @RequestBody Employee employee) {
//        employee.setId(id);  // 确保 id 被正确传递
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok("员工信息更新成功");
    }

    // 删除员工（软删除）
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("员工删除成功");
    }
}
