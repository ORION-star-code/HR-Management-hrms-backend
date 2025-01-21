package com.itheima.Controller;
import com.itheima.Service.TrainingService;
import com.itheima.pojo.TrainingActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee/training")
@CrossOrigin(origins = "*")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    // 获取员工培训记录
    @GetMapping
    public List<TrainingActivity> getTrainingRecords(@RequestParam Integer employeeId) {
        log.info("Fetching training records for employeeId: {}", employeeId);
        return trainingService.getTrainingActivitiesByEmployeeId(employeeId);
    }
}
