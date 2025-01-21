package com.itheima.Service;


import com.itheima.pojo.Training;
import com.itheima.pojo.TrainingActivity;

import java.util.List;

public interface TrainingService {

//    List<Training> getAllTrainings(); // 查询所有培训活动

    boolean addTraining(Training training); // 添加培训活动

    boolean updateTraining(Training training); // 更新培训活动

    boolean deleteTraining(Long id); // 删除培训活动}

    List<Training> getAllTrainings(int page, int limit);

    long countAllTrainings();

    List<TrainingActivity> getTrainingActivitiesByEmployeeId(Integer employeeId);
}
