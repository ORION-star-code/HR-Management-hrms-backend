package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResignationRequest {
    private Integer id;
    private Integer employeeId;
    private String reason;
    private String status;
    private Date resignationDate;
    private Date createdAt;
    private Date updatedAt;
}
