package com.sprint1.model;

public class Candidate {
    private String candidateId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String resumeLink;
    private String college;
    private String country;

    public Candidate(String candidateId, String fullName, String email, String phoneNumber, String resumeLink, String college, String country) {
        this.candidateId = candidateId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resumeLink = resumeLink;
        this.college = college;
        this.country = country;
    }

    public Candidate() {

    }

    public String getCandidateId() { return candidateId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getResumeLink() { return resumeLink; }
    public String getCollegeName() { return college; }
    public String getCountry() { return country; }


    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}