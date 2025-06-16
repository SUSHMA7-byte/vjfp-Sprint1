package com.sprint1.model;

public class Company {
    private int company_id;
    private String company_name;
    private String industry_type;
    private String contact_email;
    private String contact_phone;
    private String office_address;

    public Company(int company_id, String company_name, String industry_type, String contact_email, String contact_phone, String office_address) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.industry_type = industry_type;
        this.contact_email = contact_email;
        this.contact_phone = contact_phone;
        this.office_address = office_address;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getIndustry_type() {
        return industry_type;
    }

    public void setIndustry_type(String industry_type) {
        this.industry_type = industry_type;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                ", industry_type='" + industry_type + '\'' +
                ", contact_email='" + contact_email + '\'' +
                ", contact_phone='" + contact_phone + '\'' +
                ", office_address='" + office_address + '\'' +
                '}';
    }
}
