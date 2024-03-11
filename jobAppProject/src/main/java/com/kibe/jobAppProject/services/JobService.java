package com.kibe.jobAppProject.services;

import com.kibe.jobAppProject.entity.Job;

import java.util.ArrayList;
import java.util.List;

public interface JobService {
    List<Job> findAllJobs();
    void createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
