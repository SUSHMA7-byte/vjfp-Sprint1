package com.sprint1.dao;

import com.sprint1.service.user.CandidateUser;
import java.util.List;

public interface CandidateUserDAO {
    void addCandidate(CandidateUser candidate);
    CandidateUser getCandidateByEmail(String email);
    List<CandidateUser> getAllCandidates();
    boolean updateCandidate(CandidateUser candidate);
    boolean deleteCandidateByEmail(String email);
}
