package com.sprint1.model;

import java.time.LocalDateTime;

public abstract class Interview {
    private String interviewId;
    private LocalDateTime interviewDatetime;
    private String feedback;
    private String resultStatus;
    private String candidateId;
    private String jobId;
    private String companyId;

    public Interview(String interviewId, LocalDateTime interviewDatetime, String feedback, String resultStatus) {
        this.interviewId = interviewId;
        this.interviewDatetime = interviewDatetime;
        this.feedback = feedback;
        this.resultStatus = resultStatus;
    }

    public Interview() {}

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public LocalDateTime getInterviewDatetime() {
        return interviewDatetime;
    }

    public void setInterviewDatetime(LocalDateTime interviewDatetime) {
        this.interviewDatetime = interviewDatetime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public abstract void conductInterview();

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewDatetime=" + interviewDatetime +
                ", feedback='" + feedback + '\'' +
                ", resultStatus='" + resultStatus + '\'' +
                ", candidateId=" + candidateId +
                ", jobId=" + jobId +
                ", companyId=" + companyId +
                '}';
    }
}
