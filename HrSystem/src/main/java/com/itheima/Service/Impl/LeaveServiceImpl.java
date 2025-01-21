package com.itheima.Service.Impl;

import com.itheima.Mapper.LeaveMapper;
import com.itheima.Service.LeaveService;
import com.itheima.pojo.LeaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Slf4j
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveAndResignationMapper;

    // 更新状态
    @Override
    public boolean approveRequest(Long id, String status, String leaveType) {
        return leaveAndResignationMapper.updateStatus(id, status, leaveType) > 0;
    }

    // 拒绝申请
    @Override
    public boolean rejectRequest(Long id, String leaveType) {
        return leaveAndResignationMapper.updateStatus(id, "rejected", leaveType) > 0;
    }

    // 软删除离职申请
    @Override
    public boolean processResignation(Long id) {
        return leaveAndResignationMapper.softDeleteEmployeeRequests(id) > 0;
    }

    // 根据条件查询请假或离职申请并进行分页
    @Override
    public Map<String, Object> getRequestsByCondition(int page, int size, String name, String type, String status) {
        int offset = (page - 1) * size;

        // 查询符合条件的申请记录
        List<LeaveRequest> leaveRequests = leaveAndResignationMapper.selectByCondition(name, type, status, offset, size);

        // 获取符合条件的总记录数
        int totalCount = leaveAndResignationMapper.countByCondition(name, type, status);

        // 创建响应数据
        Map<String, Object> response = new HashMap<>();
        response.put("records", leaveRequests);  // 确保返回的数据
        response.put("totalCount", totalCount);

        return response;
    }

    // 更新申请状态
    @Override
    public boolean updateRequestStatus(Long id, String status, String leaveType) {
        // 确保在调用 Mapper 方法时传递 status 和 leaveType
        return leaveAndResignationMapper.updateStatus(id, status, leaveType) > 0;
    }
}
