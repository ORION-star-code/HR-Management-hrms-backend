package com.itheima.Mapper;

import com.itheima.pojo.Training;
import com.itheima.pojo.TrainingActivity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainingMapper {

    @Insert("INSERT INTO training_activities (name, description, start_date, end_date, location, max_participants, instructor, duration_hours) " +
            "VALUES (#{name}, #{description}, #{startDate}, #{endDate}, #{location}, #{maxParticipants}, #{instructor}, #{durationHours})")
    int insertTraining(Training training);

    @Update("<script>" +
            "UPDATE training_activities SET " +
            "<if test='name != null'>name = #{name},</if> " +
            "<if test='description != null'>description = #{description},</if> " +
            "<if test='startDate != null'>start_date = #{startDate},</if> " +
            "<if test='endDate != null'>end_date = #{endDate},</if> " +
            "<if test='location != null'>location = #{location},</if> " +
            "<if test='instructor != null'>instructor = #{instructor},</if> " +
            "<if test='durationHours != null'>duration_hours = #{durationHours},</if> " +
            "deleted = 0 " +
            "WHERE id = #{id}" +
            "</script>")
    int updateTraining(Training training);

    @Update("UPDATE training_activities SET deleted = 1 WHERE id = #{id}")
    int deleteTraining(@Param("id") Long id);

    @Select("SELECT * FROM training_activities WHERE deleted = 0 LIMIT #{offset}, #{limit}")
    @Results({
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "maxParticipants", column = "max_participants"),
            @Result(property = "instructor", column = "instructor"),
            @Result(property = "durationHours", column = "duration_hours")
    })
    List<Training> selectAllTraining(@Param("offset") int offset, @Param("limit") int limit);
    @Select("SELECT COUNT(*) FROM training_activities WHERE deleted = 0")
    long countAllTrainings();


    // 根据员工ID获取培训活动记录
    @Select("SELECT t.id, t.name, t.start_date, t.end_date, ts.score, ts.feedback " +
            "FROM training_activities t " +
            "JOIN training_scores ts ON t.id = ts.training_id " +
            "WHERE ts.employee_id = #{employeeId}")
    //对代码使用@Result
    @Results({
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
    })
    List<TrainingActivity> getTrainingActivitiesByEmployeeId(Integer employeeId);
}
