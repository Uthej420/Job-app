package com.example.project.Job;

import java.util.List;

public interface JobRepo {
    List<Job> getJobs();
    Job getJobById(int jobId);
    Job addJob(Job job,int companyId);
    Job updateJob(int jobId,Job job,int companyId);
    List<Job> deleteJob(int jobId);

}
