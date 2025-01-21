package com.itheima.Service;

import com.itheima.pojo.LeaveRequest;
import com.itheima.pojo.ResignationRequest;

public interface LeaveResignationApplicationService {
    void saveLeaveRequest(LeaveRequest leaveRequest);
    void saveResignationRequest(ResignationRequest resignationRequest);
}
