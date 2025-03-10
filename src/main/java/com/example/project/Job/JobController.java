package com.example.project.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired JobService jobService;

    @GetMapping()
    public ResponseEntity<List<Job>> getJobs(){
        return ResponseEntity.ok(jobService.getJobs());
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable("jobId") int jobId){
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }
    @PostMapping("companies/{companyId}")
    public ResponseEntity<Job> addJob(@RequestBody Job job,@PathVariable("companyId") int companyId){
        return new ResponseEntity<>(jobService.addJob(job,companyId ), HttpStatus.CREATED);
    }
    @PutMapping("/{jobId}/companies/{companyId}")
    public ResponseEntity<Job> updateJob(@PathVariable("jobId") int jobId,@RequestBody Job job,@PathVariable("companyId") int companyId){
        return  new ResponseEntity<>(jobService.updateJob(jobId,job, companyId),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{jobId}")
    public ResponseEntity<List<Job>> deleteJob(@PathVariable("jobId")int jobId){
        return new ResponseEntity<>(jobService.deleteJob(jobId),HttpStatus.MOVED_PERMANENTLY);
    }

}
