package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private Integer id;
    private Integer employeeId;
    private Date date;
    private AttendanceStatus status;
    private BigDecimal penalty;
    private String notes;
    private String employeeName;  // 员工姓名
    private Date createdAt;
}
