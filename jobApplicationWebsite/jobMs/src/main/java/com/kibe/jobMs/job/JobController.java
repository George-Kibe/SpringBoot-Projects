package com.kibe.jobMs.job;
import com.kibe.jobMs.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<JobDTO>> findAllJobs(){
        return ResponseEntity.ok(jobService.findAllJobs());
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> findJobById(@PathVariable Long id){
        JobDTO jobWithCompanyDTO = jobService.getJobById(id);
        if (jobWithCompanyDTO != null){
            return new ResponseEntity<>(jobWithCompanyDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        boolean created =  jobService.createJob(job);
        if(created){
            return new ResponseEntity<>("Job created successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Some Error Occurred!", HttpStatus.UNPROCESSABLE_ENTITY);
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
