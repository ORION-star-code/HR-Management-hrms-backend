package com.itheima.Mapper;

import com.itheima.pojo.LeaveRequest;
import com.itheima.pojo.ResignationRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface LeaveResignationApplicationMapper {


    @Insert("INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, reason, status, approver_id, comments) " +
            "VALUES (#{employeeId}, #{leaveType}, #{startDate}, #{endDate}, #{reason}, #{status}, #{approverId}, #{comments})")
    @Results({
            @Result(property = "leaveType", column = "leave_type"),
    })
    void insertLeaveRequest(LeaveRequest leaveRequest);

    @Insert("INSERT INTO resignation_requests (employee_id, reason, status, approver_id, comments) " +
            "VALUES (#{employeeId}, #{reason}, #{status}, #{approverId}, #{comments})")
    void insertResignationRequest(ResignationRequest resignationRequest);
}
