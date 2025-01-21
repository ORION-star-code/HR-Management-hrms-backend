package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    private Long id;
    private Long employeeId;
    private BigDecimal baseSalary;
    private BigDecimal performanceBonus;
    private BigDecimal attendanceBonus;
    private LocalDate paymentDate;  // 将 Date 类型更改为 LocalDate
    private BigDecimal totalSalary;
    private String remarks;
    private LocalDate salaryMonth;  // 将 Date 类型更改为 LocalDate
}
