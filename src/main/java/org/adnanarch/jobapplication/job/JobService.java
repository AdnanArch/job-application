package org.adnanarch.jobapplication.job;

import java.util.ArrayList;

public interface JobService {
    ArrayList<Job> findAllJobs();
    void createJob(Job job);

    Job findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJob);
}
