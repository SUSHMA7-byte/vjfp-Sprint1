package com.sprint1.service.CommonService;

import com.sprint1.model.Candidate;
import com.sprint1.model.Company;
import com.sprint1.model.Job;

import java.util.List;
import java.util.Map;

public interface CommonService {

    List<Job> getAllJobs();
    List<Company> getCompaniesByLocation(String location);

    Map<String, Integer> getJobCountGroupedByLocation();
    Map<String, Integer> getApplicationStats();

    List<Candidate> getAllSelectedCandidates();
}