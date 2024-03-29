package com.kibe.jobAppProject.controllers;

import com.kibe.jobAppProject.entity.Job;
import com.kibe.jobAppProject.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Server health Check: Server is running fine!");
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAllJobs(){
        return ResponseEntity.ok(jobService.findAllJobs());
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if (job != null){
           return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added successfully!", HttpStatus.OK);
    }
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if (deleted){
            return new ResponseEntity<>("Job of ID "+ id+" Deleted Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // @PutMapping("/jobs/{id}")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if(updated){
            return ResponseEntity.ok("Job updated successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
