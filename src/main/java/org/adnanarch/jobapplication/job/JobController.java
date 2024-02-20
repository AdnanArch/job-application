package org.adnanarch.jobapplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        Job job = jobService.findJobById(id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if (deleted){
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PutMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean isUpdated = jobService.updateJobById(id, updatedJob);
        if (isUpdated){
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
