package com.example.activityService.repository;

import com.example.activityService.models.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRespository extends MongoRepository<Activity,String> {
}
