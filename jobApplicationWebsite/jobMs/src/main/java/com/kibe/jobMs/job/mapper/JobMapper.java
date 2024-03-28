package com.kibe.jobMs.job.mapper;

import com.kibe.jobMs.dto.JobDTO;
import com.kibe.jobMs.external.Company;
import com.kibe.jobMs.external.Review;
import com.kibe.jobMs.job.Job;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(
            Job job,
            Company company,
            List<Review> reviews
    ){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }

}





