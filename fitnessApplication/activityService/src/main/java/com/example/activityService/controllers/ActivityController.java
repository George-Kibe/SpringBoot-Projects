package com.example.activityService.controllers;

import com.example.activityService.dto.ActivityRequest;
import com.example.activityService.dto.ActivityResponse;
import com.example.activityService.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
    @GetMapping
    public String ping() {
        return "pong";
    }
    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest activityRequest) {
        ActivityResponse activityResponse = activityService.createActivity(activityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(activityResponse);
    }
}
