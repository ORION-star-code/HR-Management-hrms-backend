package com.itheima.Controller;

import com.itheima.Service.TrainingService;
import com.itheima.pojo.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/training")
@CrossOrigin(origins = "*")
public class ManageTrainingController {
    @Autowired
    private TrainingService trainingService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllTrainings(@RequestParam int page, @RequestParam int limit) {
        List<Training> trainings = trainingService.getAllTrainings(page, limit);
        long totalTrainings = trainingService.countAllTrainings();
        Map<String, Object> response = new HashMap<>();
        response.put("activities", trainings);
        response.put("totalPages", (totalTrainings + limit - 1) / limit);
        log.info("Total trainings fetched: {}", totalTrainings);
        log.info("Training data retrieved: {}", trainings);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTraining(@RequestBody Training training) {
        log.info("Received training: {}", training);
        trainingService.addTraining(training);
        return ResponseEntity.ok("培训活动添加成功");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editTraining(@PathVariable Long id, @RequestBody Training training) {
        trainingService.updateTraining(training);
        return ResponseEntity.ok("培训活动更新成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.ok("培训活动删除成功");
    }
}
