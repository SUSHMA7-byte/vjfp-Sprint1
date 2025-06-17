package com.sprint1.model;

public class Company {
    private int companyId;
    private String companyName;
    private String industryType;
    private String contactEmail;
    private String password;

    public Company(int companyId, String companyName, String industryType, String contactEmail, String password) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.industryType = industryType;
        this.contactEmail = contactEmail;
        this.password = password;
    }

    public Company() {
    }

    public int getCompanyId() { return companyId; }
    public String getCompanyName() { return companyName; }
    public String getIndustryType() { return industryType; }
    public String getContactEmail() { return contactEmail; }
    public String getPassword() { return password; }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
