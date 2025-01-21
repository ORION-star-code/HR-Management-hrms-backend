package com.itheima.Mapper;

import com.itheima.pojo.LeaveRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LeaveMapper {

    // 查询所有申请记录（未被软删除）并分页
    @Select("SELECT * FROM leave_requests WHERE status != 'DELETED' LIMIT #{offset}, #{limit}")
    List<LeaveRequest> selectAllRequests(@Param("offset") int offset, @Param("limit") int limit);

    // 根据条件查询申请记录（分页）
    @Select("<script>" +
            "SELECT lr.id, lr.employee_id, lr.leave_type, e.name AS employee_name, lr.start_date, lr.end_date, lr.reason, lr.status, lr.approver_id, lr.comments, lr.created_at, lr.updated_at " +
            "FROM leave_requests lr " +
            "LEFT JOIN employees e ON lr.employee_id = e.id " +
            "WHERE lr.status != 'DELETED' " +
            "<if test='name != null and name != \"\"'>AND e.name LIKE CONCAT('%', #{name}, '%')</if> " +
            "<if test='type != null and type != \"\"'>AND lr.leave_type = #{type}</if> " +
            "<if test='status != null and status != \"\"'>AND lr.status = #{status}</if> " +
            "LIMIT #{offset}, #{limit}" +
            "</script>")
    @Results({
            @Result(property = "employeeName", column = "employee_name"),
            @Result(property = "leaveType", column = "leave_type"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "approverId", column = "approver_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<LeaveRequest> selectByCondition(@Param("name") String name, @Param("type") String type,
                                         @Param("status") String status, @Param("offset") int offset, @Param("limit") int limit);

    // 获取符合条件的记录总数
    @Select("<script>" +
            "SELECT COUNT(*) FROM leave_requests WHERE status != 'DELETED' " +
            "<if test='name != null and name != \"\"'>AND name LIKE CONCAT('%', #{name}, '%')</if> " +
            "<if test='type != null and type != \"\"'>AND type = #{type}</if> " +
            "<if test='status != null and status != \"\"'>AND status = #{status}</if> " +
            "</script>")
    int countByCondition(@Param("name") String name, @Param("type") String type,
                         @Param("status") String status);

    // 更新申请状态
    @Update("UPDATE leave_requests SET status = #{status}, leave_type = #{type} WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") String status, @Param("type") String type);

    // 软删除离职员工的申请记录
    @Update("UPDATE leave_requests SET status = 'DELETED' WHERE id = #{id}")
    int softDeleteEmployeeRequests(@Param("id") Long id);
}
