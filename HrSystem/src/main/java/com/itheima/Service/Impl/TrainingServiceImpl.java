package com.itheima.Service.Impl;

import com.itheima.Mapper.TrainingMapper;
import com.itheima.Service.TrainingService;
import com.itheima.pojo.Training;
import com.itheima.pojo.TrainingActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingMapper trainingMapper;

    @Override
    public boolean addTraining(Training training) {
        log.info("Received training data: " + training);
        return trainingMapper.insertTraining(training) > 0;
    }

    @Override
    public boolean updateTraining(Training training) {
        return trainingMapper.updateTraining(training) > 0;
    }

    @Override
    public boolean deleteTraining(Long id) {
        return trainingMapper.deleteTraining(id) > 0;
    }

    @Override
    public List<Training> getAllTrainings(int page, int limit) {
        int offset = (page - 1) * limit;
        return trainingMapper.selectAllTraining(offset, limit);
    }

    @Override
    public long countAllTrainings() {
        return trainingMapper.countAllTrainings();
    }
    @Override
    public List<TrainingActivity> getTrainingActivitiesByEmployeeId(Integer employeeId) {
        log.info("Fetching training activities for employeeId: {}", employeeId);
        return trainingMapper.getTrainingActivitiesByEmployeeId(employeeId);
    }
}
