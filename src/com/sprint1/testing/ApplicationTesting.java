package com.sprint1.testing;

import java.sql.SQLException;

public class ApplicationTesting implements Runnable {
    private int candidateId;
    private String jobId;

    public ApplicationTesting(int candidateId, String jobId) {
        this.candidateId = candidateId;
        this.jobId = jobId;
    }

    @Override
    public void run() {
        try {
            // do application creation
            System.out.println("Application Created for Candidate ID: " + candidateId + " for Job ID: " + jobId);
            throw new SQLException(); // remove in prod
        } catch (SQLException e) {
            System.err.println("Error creating application for Candidate ID: " + candidateId);
        }
    }
}
