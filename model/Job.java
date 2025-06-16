package com.sprint1.model;

import java.time.LocalDateTime;

public class Job {
    private int jobId;
    private String jobTitle;
    private String jobDescription;
    private double salary;
    private int totalOpenings;
    private LocalDateTime application_start_date;
    private LocalDateTime application_end_date;
    private String jobLocation;
    private String jobType;

    public Job(int jobId, String jobTitle, String jobDescription, double salary, int totalOpenings, LocalDateTime application_start_date, LocalDateTime application_end_date, String jobLocation, String jobType) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.totalOpenings = totalOpenings;
        this.application_start_date = application_start_date;
        this.application_end_date = application_end_date;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getTotalOpenings() {
        return totalOpenings;
    }

    public void setTotalOpenings(int totalOpenings) {
        this.totalOpenings = totalOpenings;
    }

    public LocalDateTime getApplication_start_date() {
        return application_start_date;
    }

    public void setApplication_start_date(LocalDateTime application_start_date) {
        this.application_start_date = application_start_date;
    }

    public LocalDateTime getApplication_end_date() {
        return application_end_date;
    }

    public void setApplication_end_date(LocalDateTime application_end_date) {
        this.application_end_date = application_end_date;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
