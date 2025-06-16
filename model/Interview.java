package com.sprint1.model;

import java.time.LocalDateTime;

public abstract class Interview {
    private int interviewId;
    private LocalDateTime interviewDatetime;
    private String feedback;
    private String resultStatus;

    public Interview(int interviewId, LocalDateTime interviewDatetime, String feedback, String resultStatus) {
        this.interviewId = interviewId;
        this.interviewDatetime = interviewDatetime;
        this.feedback = feedback;
        this.resultStatus = resultStatus;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
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

    public abstract void conductInterview();

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewDatetime=" + interviewDatetime +
                ", feedback='" + feedback + '\'' +
                ", resultStatus='" + resultStatus + '\'' +
                '}';
    }
}
