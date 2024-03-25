package com.kibe.jobMs.job;
import com.kibe.jobMs.dto.JobWithCompanyDTO;
import com.kibe.jobMs.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImplementation implements JobService{
    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    RestTemplate restTemplate;

    public JobServiceImplementation(JobRepository jobRepository, RestTemplate restTemplate) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
    }

    // private Long nextId = 1L;
    private JobWithCompanyDTO convertToDTO(Job job){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        // RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://COMPANYMS:8083/companies/" + job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }
    @Override
    public List<JobWithCompanyDTO> findAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();
        for (Job job: jobs){
            JobWithCompanyDTO jobWithCompanyDTO = convertToDTO(job);
            jobWithCompanyDTOs.add(jobWithCompanyDTO);
        }
        //return jobWithCompanyDTOs;
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean createJob(Job job) {
        // job.setId(nextId ++);
        try{
            jobRepository.save(job);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public JobWithCompanyDTO getJobById(Long id) {

        Job job  = jobRepository.findById(id).orElse(null);
        if(job != null){
            return  convertToDTO(job);
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e){
            System.out.println("Encountered Exception: " + e);
            return false;
        }
    }
    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            return true;
        }
        return false;
    }
}
