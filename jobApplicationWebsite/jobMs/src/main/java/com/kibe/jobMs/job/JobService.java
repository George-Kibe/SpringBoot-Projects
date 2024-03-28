package com.kibe.jobMs.job;
import com.kibe.jobMs.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAllJobs();
    boolean createJob(Job job);
    JobDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}

