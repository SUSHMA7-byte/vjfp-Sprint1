package com.sprint1.service.CompanyService;

import com.sprint1.model.*;

import java.util.List;

public interface CompanyService {

    void postJob(Job job);
    void updateJob(Job job);
    void deleteJob(int jobId);
    Job getJobById(int jobId);

    List<Job> getJobsByCompany(int companyId);
    List<Job> getJobsByLocation(String location);
    List<Job> getJobsByType(String jobType);
    List<Interview> getInterviewsByJob(int jobId);

    List<Application> getApplicationsByJobId(int jobId);
    int countApplicationsForJob(int jobId);

    List<Candidate> getSelectedCandidatesByJob(int jobId);

    void updateInterviewResult(int interviewId, String resultStatus);
    void closeJobPosting(int jobId);
}
