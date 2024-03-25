package com.kibe.jobMs.job.mapper;

import com.kibe.jobMs.dto.JobWithCompanyDTO;
import com.kibe.jobMs.external.Company;
import com.kibe.jobMs.job.Job;

public class JobMapper {
    public static JobWithCompanyDTO mapToJobWithCompanyDTO(
            Job job,
            Company company
    ){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }

}





