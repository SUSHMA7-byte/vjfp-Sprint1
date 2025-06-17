package com.sprint1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Job {
    private String jobId;
    private String jobTitle;
    private String jobDescription;
    private double salaryPackage;
    private int totalOpenings;
    private LocalDate applicationStartDate;
    private LocalDate applicationEndDate;
    private String jobLocation;
    private String jobType;
    private String companyId;

    public Job() {}

    public Job(String jobId, String jobTitle, String jobDescription, double salaryPackage, int totalOpenings,
               LocalDate applicationStartDate, LocalDate applicationEndDate,
               String jobLocation, String jobType) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.salaryPackage = salaryPackage;
        this.totalOpenings = totalOpenings;
        this.applicationStartDate = applicationStartDate;
        this.applicationEndDate = applicationEndDate;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
    }

    public Job(String jobId, String jobTitle, String jobDescription, double salaryPackage, int totalOpenings, LocalDateTime applicationStartDate, LocalDateTime applicationEndDate, String jobLocation, String jobType) {
    }

    // Getters and setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
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

    public double getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(double salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public int getTotalOpenings() {
        return totalOpenings;
    }

    public void setTotalOpenings(int totalOpenings) {
        this.totalOpenings = totalOpenings;
    }

    public LocalDate getApplicationStartDate() {
        return applicationStartDate;
    }

    public void setApplicationStartDate(LocalDate applicationStartDate) {
        this.applicationStartDate = applicationStartDate;
    }

    public LocalDate getApplicationEndDate() {
        return applicationEndDate;
    }

    public void setApplicationEndDate(LocalDate applicationEndDate) {
        this.applicationEndDate = applicationEndDate;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return String.format(
                "Job ID: %s\nTitle: %s\nLocation: %s\nType: %s\nSalary: ₹%.2f\nOpenings: %d\nApplication Window: %s to %s",
                jobId, jobTitle, jobLocation, jobType, salaryPackage, totalOpenings,
                applicationStartDate, applicationEndDate
        );
    }
}
