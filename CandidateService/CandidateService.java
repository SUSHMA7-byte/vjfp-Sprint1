package com.sprint1.service.CandidateService;

import com.sprint1.model.Application;
import com.sprint1.model.Candidate;
import com.sprint1.model.Interview;
import com.sprint1.model.Job;

import java.util.List;

public interface CandidateService {

    void registerCandidate(Candidate candidate);
    void updateResume(int candidateId, String resumeLink);

    void applyToJob(int candidateId, int jobId);
    void withdrawApplication(int applicationId);

    List<Job> getOpenJobs();
    List<Job> getJobsBySalaryRange(double min, double max);
    List<Job> getJobsByQualification(String qualification);

    List<Application> getApplicationsByCandidate(int candidateId);
    Interview getInterviewByApplicationId(int applicationId);
    List<Interview> getAllInterviewsByCandidate(int candidateId);
}
