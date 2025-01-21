package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private Integer userId;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private String position;
    private String department;
    private String emergencyContact;
    private Date hireDate;
    private BigDecimal salaryBase;
    private BigDecimal performanceBonus;
    private BigDecimal fullAttendanceBonus;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private String avatar;


}
