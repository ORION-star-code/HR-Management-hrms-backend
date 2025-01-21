package com.itheima.Service;

import com.itheima.pojo.LeaveRequest;

import java.util.List;
import java.util.Map;

public interface LeaveService {
//    List<LeaveRequest> getAllRequests(int offset, int limit); // 查询所有请假与离职申请

    // 更新申请状态
    boolean approveRequest(Long id, String status, String leaveType);

    // 拒绝申请
    boolean rejectRequest(Long id, String leaveType);

    // 软删除离职申请
    boolean processResignation(Long id);

    // 根据条件查询请假或离职申请并进行分页
    Map<String, Object> getRequestsByCondition(int page, int size, String name, String type, String status);

    // 更新申请状态
    boolean updateRequestStatus(Long id, String status,String leaveType);
}
