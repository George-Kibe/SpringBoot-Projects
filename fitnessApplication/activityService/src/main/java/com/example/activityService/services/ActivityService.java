package com.example.activityService.services;

import com.example.activityService.dto.ActivityRequest;
import com.example.activityService.dto.ActivityResponse;
import com.example.activityService.models.Activity;
import com.example.activityService.repository.ActivityRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRespository activityRespository;

    public ActivityResponse createActivity(ActivityRequest activityRequest) {
        Activity activity =  Activity.builder()
            .userId(activityRequest.getUserId())
            .type(activityRequest.getType())
            .duration(activityRequest.getDuration())
            .startTime(activityRequest.getStartTime())
            .caloriesBurnt(activityRequest.getCaloriesBurnt())
            .additionalMetrics(activityRequest.getAdditionalMetrics())
            .build();
        Activity savedActivity = activityRespository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setType(activity.getType());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setCaloriesBurnt(activity.getCaloriesBurnt());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());
        return activityResponse;
    }
}
