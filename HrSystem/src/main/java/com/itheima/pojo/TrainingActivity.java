package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingActivity {
    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Double score;
    private String feedback;
}
