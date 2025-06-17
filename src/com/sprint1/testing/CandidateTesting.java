package com.sprint1.testing;

import com.sprint1.model.Candidate;
import java.util.*;

import java.sql.SQLException;

public class CandidateTesting implements Runnable {
    private Candidate candidate;

    public CandidateTesting(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public void run() {
        try {
            // replace with JDBC save logic
            System.out.println("Candidate Created in DB: " + candidate.getFullName());
            throw new SQLException(); // remove in prod
        } catch (SQLException e) {
            System.err.println("Error saving candidate: " + candidate.getFullName());
        }
    }

    public static List<Candidate> generateTestingCandidates(int count) {
        List<Candidate> candidates = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            candidates.add(new Candidate(
                    0,
                    "test_candidate_" + i,
                    "test_candidate_" + i + "@mail.com",
                    "",
                    "",
                    "",
                    ""
            ));
        }
        return candidates;
    }
}
