package com.kibe.jobMs.job;
import com.kibe.jobMs.dto.JobWithCompanyDTO;

import java.util.ArrayList;
import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAllJobs();
    boolean createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}

