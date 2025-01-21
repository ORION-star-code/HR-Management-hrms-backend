package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
//    private int id;
//    private int employeeId; // employee_id 在数据库中
//    private String leaveType; // leave_type 在数据库中
//    private String employeeName; // 如果需要，可以通过关联查询获取
//    private Date startDate; // start_date 在数据库中
//    private Date endDate; // end_date 在数据库中
//    private String reason;
//    private String status;
//    private Long approverId; // approver_id 在数据库中
//    private String comments;
//    private Date createdAt; // created_at 在数据库中
//    private Date updatedAt; // updated_at 在数据库中
    private Long id;
    private Long employeeId;
    private String leaveType;
    private String employeeName; // 确保有这个字段
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private Long approverId;
    private String comments;
    private Date createdAt;
    private Date updatedAt;


}
