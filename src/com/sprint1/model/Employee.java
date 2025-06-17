package com.sprint1.model;

public class Employee {
    private int employee_id;
    private String full_name;
    private String role;
    private String contact_number;
    private String email;

    public Employee(int employee_id, String full_name, String role, String contact_number, String email) {
        this.employee_id = employee_id;
        this.full_name = full_name;
        this.role = role;
        this.contact_number = contact_number;
        this.email = email;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", full_name='" + full_name + '\'' +
                ", role='" + role + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
