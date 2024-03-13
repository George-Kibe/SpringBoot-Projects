package com.kibe.jobMs.job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService{
    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // private Long nextId = 1L;
    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
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
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id)){
//                // jobs.remove(job);
//                iterator.remove();
//                return true;
//            }
//        }
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
