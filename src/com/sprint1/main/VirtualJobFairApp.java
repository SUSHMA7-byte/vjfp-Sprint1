package com.sprint1.main;

import com.sprint1.dao.CandidateDAO;
import com.sprint1.model.Candidate;
import com.sprint1.model.Company;
import com.sprint1.service.candidate.CandidateServiceImpl;
import com.sprint1.service.company.CompanyServiceImpl;

import com.sprint1.util.DBUtil;
import java.sql.*;

import java.util.Scanner;

public class VirtualJobFairApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== VIRTUAL JOB FAIR PORTAL =====");
            System.out.println("1. Candidate");
            System.out.println("2. Company");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                case 0:
                    // Test Database Connection
                    try {
                        Connection conn = DBUtil.getConnection();

                        String query = "SHOW TABLES";
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        System.out.println("Tables in the database:");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }

                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (Exception e) {
                        System.out.println("DATABASE ERROR: " + e.getMessage());
                        e.printStackTrace(); // Don't ship this to prod, but fine for dev/debug
                    }

                case 1:
                    System.out.println("\n===== CANDIDATE PORTAL =====");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.print("Choose an option: ");
                    int candidateChoice = sc.nextInt();
                    sc.nextLine();

                    CandidateServiceImpl candidateService = new CandidateServiceImpl();
                    CandidateDAO candidateDAO = new CandidateDAO();
                    Candidate currentCandidate = null;

                    if (candidateChoice == 1) {
                        System.out.print("Enter Full Name: ");
                        String fullName = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phone = sc.nextLine();
                        System.out.print("Enter Resume Link: ");
                        String resume = sc.nextLine();
                        System.out.print("Enter College Name: ");
                        String college = sc.nextLine();
                        System.out.print("Enter Country: ");
                        String country = sc.nextLine();

                        Candidate newCandidate = new Candidate();
                        newCandidate.setFullName(fullName);
                        newCandidate.setEmail(email);
                        newCandidate.setPhoneNumber(phone);
                        newCandidate.setResumeLink(resume);
                        newCandidate.setCollege(college);
                        newCandidate.setCountry(country);

                        candidateDAO.registerCandidate(newCandidate);
                        currentCandidate = newCandidate; // now has generated UUID inside
                        System.out.println("Registration successful. Your Candidate ID is: " + currentCandidate.getCandidateId());

                    } else if (candidateChoice == 2) {
                        System.out.print("Enter Registered Email: ");
                        String email = sc.nextLine();

                        Candidate loggedInCandidate = candidateDAO.getCandidateByEmail(email);
                        if (loggedInCandidate != null) {
                            currentCandidate = loggedInCandidate;
                            System.out.println("Login successful. Welcome, " + loggedInCandidate.getFullName());
                        } else {
                            System.out.println("Login failed. Email not found.");
                            break;
                        }
                    } else {
                        System.out.println("Invalid option.");
                        break;
                    }

                    if (currentCandidate != null) {
                        candidateService.candidateDashboard(sc, currentCandidate); // Pass the full object
                    }
                    break;

                case 2:
                    System.out.println("\n===== COMPANY PORTAL =====");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.print("Choose an option: ");
                    int companyChoice = sc.nextInt();
                    sc.nextLine();

                    CompanyServiceImpl companyService = new CompanyServiceImpl();
                    int companyId = -1;

                    if (companyChoice == 1) {
                        System.out.print("Enter Company Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Industry Type: ");
                        String industry = sc.nextLine();
                        System.out.print("Enter Contact Email: ");
                        String email = sc.nextLine();

                        Company newCompany = new Company();
                        newCompany.setCompanyName(name);
                        newCompany.setIndustryType(industry);
                        newCompany.setContactEmail(email);

                        companyId = companyService.registerCompany(newCompany); // returns generated company ID
                        System.out.println("Registration successful. Your Company ID is: " + companyId);

                    } else if (companyChoice == 2) {
                        System.out.print("Enter Registered Email: ");
                        String email = sc.nextLine();

                        Company loggedInCompany = companyService.loginCompany(email); // fetch by email
                        if (loggedInCompany != null) {
                            companyId = loggedInCompany.getCompanyId();
                            System.out.println("Login successful. Welcome, " + loggedInCompany.getCompanyName());
                        } else {
                            System.out.println("Login failed. Email not found.");
                            break;
                        }
                    } else {
                        System.out.println("Invalid option.");
                        break;
                    }

                    if (companyId != -1) {
                        companyService.companyDashboard(sc, String.valueOf(companyId));
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the Virtual Job Fair Portal!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
