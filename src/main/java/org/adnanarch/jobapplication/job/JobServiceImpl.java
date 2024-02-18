package org.adnanarch.jobapplication.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;


@Service
public class JobServiceImpl implements JobService{
    private final ArrayList<Job> jobsList = new ArrayList<>();
    private Long nextId = 1L;
    @Override
    public ArrayList<Job> findAllJobs() {
        return jobsList;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobsList.add(job);
    }

    @Override
    public Job findJobById(Long id) {
        for (Job job : jobsList){
            if (job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        Iterator<Job> iterator = jobsList.iterator();
        while (iterator.hasNext()){
            Job job = iterator.next();
            if (job.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        for (Job job : jobsList){
            if (job.getId().equals(id)){
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMinSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
}
