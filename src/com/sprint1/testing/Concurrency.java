package com.sprint1.testing;

import com.sprint1.model.Candidate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrency {
    private String jobId;
    private int threadCount;

    public Concurrency(String jobId, int threadCount) {
        this.jobId = jobId;
        this.threadCount = threadCount;
    }

    public void runFullTestPipeline() {
        List<Candidate> candidates = CandidateTesting.generateTestingCandidates(threadCount);

//        multithreaded candidate creation
        ExecutorService candidateExecutor = Executors.newFixedThreadPool(threadCount);
        for (Candidate candidate : candidates) {
            candidateExecutor.execute(new CandidateTesting(candidate));
        }
        candidateExecutor.shutdown();
        while (!candidateExecutor.isTerminated()) {
//            wait
        }
        System.out.println("All candidates created in DB.");

//        multithreaded job application creation
        ExecutorService appExecutor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount && i < candidates.size(); i++) {
            int candidateId = candidates.get(i).getCandidateId();
            appExecutor.execute(new ApplicationTesting(candidateId, this.jobId));
        }
        appExecutor.shutdown();
        System.out.println("All application tests triggered.");
    }
}
