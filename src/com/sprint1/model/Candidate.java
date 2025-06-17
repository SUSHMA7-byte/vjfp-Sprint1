package com.sprint1.model;

import com.sprint1.service.job.JobService;
import com.sprint1.service.job.JobServiceImpl;
import com.sprint1.model.Job;
import com.sprint1.model.Interview;
import com.sprint1.service.interview.HRInterviewService;
import com.sprint1.service.interview.TechnicalInterviewService;
import com.sprint1.service.notification.EmailNotification;
import com.sprint1.service.notification.Notification;
import com.sprint1.service.notification.SMSNotification;

import java.time.LocalDateTime;
import java.util.*;

public class Candidate {
    private int candidateId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String resumeLink;
    private String college;
    private String country;
    private String passwordHash;


    private List<Job> jobList = new ArrayList<>();
    private Map<Integer, List<Integer>> appliedJobsMap = new HashMap<>();

    public Candidate() {
        // Sample datas I've added here. Here JDBC must play its role
        jobList.add(new Job(101, "Java Developer", "Backend Developer Role", 60000, 5, LocalDateTime.now(), LocalDateTime.now().plusDays(30), "Bangalore", "Full-Time"));
        jobList.add(new Job(102, "Frontend Developer", "React Developer Role", 55000, 3, LocalDateTime.now(), LocalDateTime.now().plusDays(30), "Remote", "Part-Time"));
    }

    public Candidate(int id, String name, String email, String phone, String resume, String college, String country) {
        this.candidateId = id;
        this.fullName = name;
        this.email = email;
        this.phoneNumber = phone;
        this.resumeLink = resume;
        this.college = college;
        this.country = country;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void candidateDashboard(Scanner sc) {
        JobService jobService = new JobServiceImpl();

        System.out.println("\n===== WELCOME CANDIDATE =====");
        System.out.print("Enter your Candidate ID to Login: ");
        this.candidateId = sc.nextInt();
        sc.nextLine();

        // fetch this from JDBC
        this.fullName = "Sushma";
        this.email = "sushma@mailg.com";
        this.phoneNumber = "9876543210";
        this.resumeLink = "resume_link";
        this.college = "XYZ College";
        this.country = "India";
        this.passwordHash = "secureHash";

        System.out.println("Welcome, " + this.fullName);

        int choice;
        do {
            System.out.println("\n===== CANDIDATE DASHBOARD =====");
            System.out.println("1. View All Active Jobs");
            System.out.println("2. Apply for Job");
            System.out.println("3. View My Applications");
            System.out.println("4. View Scheduled Interviews");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    for (Job job : jobList) {
                        jobService.displayJobDetails(job);
                        System.out.println("------------------------");
                    }
                    break;

                case 2:
                    System.out.print("Enter Job ID to apply: ");
                    int jobId = sc.nextInt();
                    sc.nextLine();

                    boolean found = false;
                    for (Job job : jobList) {
                        if (job.getJobId() == jobId) {
                            found = true;
                            jobService.applyForJob(this, String.valueOf(jobId));
                            appliedJobsMap.putIfAbsent(candidateId, new ArrayList<>());
                            appliedJobsMap.get(candidateId).add(jobId);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Invalid Job ID");
                    }
                    break;

                case 3:
                    List<Integer> appliedJobs = appliedJobsMap.get(candidateId);
                    if (appliedJobs == null || appliedJobs.isEmpty()) {
                        System.out.println("You haven't applied for any jobs yet.");
                    } else {
                        System.out.println("Jobs Applied:");
                        for (int id : appliedJobs) {
                            for (Job job : jobList) {
                                if (job.getJobId() == id) {
                                    jobService.displayJobDetails(job);
                                    System.out.println("------------------------");
                                }
                            }
                        }
                    }
                    break;

                case 4:
                    Interview interview;
                    interview = new HRInterviewService(); // or TechnicalInterviewService()
                    LocalDateTime scheduledTime = LocalDateTime.now().plusDays(1);
                    interview.setInterviewDatetime(scheduledTime);

                    System.out.println("Your interview has been scheduled.");
                    System.out.println("Interview Type: " + interview.getClass().getSimpleName().replace("Service", ""));
                    System.out.println("Scheduled on: " + interview.getInterviewDatetime());

                    Notification notification;
                    String contactInfo;
                    if (this.phoneNumber != null && !this.phoneNumber.isEmpty()) {
                        notification = new SMSNotification();
                        contactInfo = this.phoneNumber;
                    } else {
                        notification = new EmailNotification();
                        contactInfo = this.email;
                    }

                    notification.send(contactInfo, "Your interview is scheduled on: " + interview.getInterviewDatetime());
                    break;


                case 5:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }
}
