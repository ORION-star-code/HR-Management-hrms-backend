package com.itheima.Service.Impl;

import com.itheima.Mapper.LeaveResignationApplicationMapper;
import com.itheima.Service.LeaveResignationApplicationService;
import com.itheima.pojo.LeaveRequest;
import com.itheima.pojo.ResignationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveResignationApplicationServiceImpl implements LeaveResignationApplicationService {

    @Autowired
    private LeaveResignationApplicationMapper applicationMapper;

    @Override
    public void saveLeaveRequest(LeaveRequest leaveRequest) {
        applicationMapper.insertLeaveRequest(leaveRequest);
    }

    @Override
    public void saveResignationRequest(ResignationRequest resignationRequest) {
        applicationMapper.insertResignationRequest(resignationRequest);
    }
}
