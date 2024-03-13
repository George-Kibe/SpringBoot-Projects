package com.kibe.jobMs.job;
import java.util.ArrayList;
import java.util.List;

public interface JobService {
    List<Job> findAllJobs();
    boolean createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}

