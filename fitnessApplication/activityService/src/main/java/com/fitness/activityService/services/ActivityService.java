package com.fitness.activityService.services;

import com.fitness.activityService.dto.ActivityRequest;
import com.fitness.activityService.dto.ActivityResponse;
import com.fitness.activityService.models.Activity;
import com.fitness.activityService.repository.ActivityRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRespository activityRespository;
    private final UserValidationService userValidationService;

    @Value()
    private String exchange;
    private String routing;

    public ActivityResponse createActivity(ActivityRequest activityRequest) {
        // validate user from userId
        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());
        if (!isValidUser) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user id");
        }
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

    public List<ActivityResponse> getUserActivities(String userId) {
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId is null");
        }
        List<Activity> activities = activityRespository.findByUserId(userId);
        return activities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivity(String activityId) {
        Optional<Activity> activity = activityRespository.findById(activityId);
        if (activity.isPresent()) {
            return mapToResponse(activity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity with that ID not found!");
        }
    }
}
