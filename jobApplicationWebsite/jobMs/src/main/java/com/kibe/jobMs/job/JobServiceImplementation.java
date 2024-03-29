package com.kibe.jobMs.job;
import com.kibe.jobMs.dto.JobDTO;
import com.kibe.jobMs.external.Company;
import com.kibe.jobMs.external.Review;
import com.kibe.jobMs.job.clients.CompanyClient;
import com.kibe.jobMs.job.clients.ReviewClient;
import com.kibe.jobMs.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    CompanyClient companyClient;
    ReviewClient reviewClient;

    public JobServiceImplementation(JobRepository jobRepository, RestTemplate restTemplate, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    // private Long nextId = 1L;
    private JobDTO convertToDTO(Job job){
        // rest template style
        // Company company = restTemplate.getForObject("http://COMPANYMS:8083/companies/" + job.getCompanyId(), Company.class);

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://REVIEWMS:8084/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviewsRest = reviewResponse.getBody();
        // using openfeign
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        return JobMapper.mapToJobWithCompanyDTO(
                job, company, reviews
        );
    }
    int attempt  = 1;
    @Override
    // @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyFallbackMethod")
    // @Retry(name = "companyBreaker", fallbackMethod = "companyFallbackMethod")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyFallbackMethod")
    public List<JobDTO> findAllJobs() {
        System.out.println("Attempt: " + ++attempt);
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job: jobs){
            JobDTO jobDTO = convertToDTO(job);
            jobDTOs.add(jobDTO);
        }
        //return jobWithCompanyDTOs;
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<String> companyFallbackMethod(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Under Maintenance. Please try after some time");
        return list;
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
    public JobDTO getJobById(Long id) {

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
