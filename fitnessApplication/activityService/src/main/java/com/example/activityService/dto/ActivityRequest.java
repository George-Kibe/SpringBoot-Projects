package com.example.activityService.dto;

import com.example.activityService.models.ActivityType;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurnt;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
}
