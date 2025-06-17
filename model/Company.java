package com.sprint1.model;

import com.sprint1.model.Job;
import com.sprint1.model.Candidate;
import com.sprint1.model.Interview;
import com.sprint1.service.interview.HRInterviewService;
import com.sprint1.service.interview.TechnicalInterviewService;

import java.time.LocalDateTime;
import java.util.*;

public class Company {
    private int company_id;
    private String company_name;
    private String industry_type;
    private String contact_email;
    private String contact_phone;
    private String office_address;

    private List<Job> postedJobs = new ArrayList<>();
    private Map<Integer, List<Candidate>> jobApplications = new HashMap<>();
    private Map<Integer, Interview> scheduledInterviews = new HashMap<>();

    public Company(int company_id, String company_name, String industry_type, String contact_email, String contact_phone, String office_address) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.industry_type = industry_type;
        this.contact_email = contact_email;
        this.contact_phone = contact_phone;
        this.office_address = office_address;
    }

    public Company() {

    }



    public void companyDashboard(Scanner sc) {
        int choice;
        do {
            System.out.println("\n===== COMPANY DASHBOARD =====");
            System.out.println("1. Post a New Job");
            System.out.println("2. View All Posted Jobs");
            System.out.println("3. View Applications to My Jobs");
            System.out.println("4. Schedule Interview");
            System.out.println("5. View Scheduled Interviews");
            System.out.println("6. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    postNewJob(sc);
                    break;
                case 2:
                    viewPostedJobs();
                    break;
                case 3:
                    viewApplications();
                    break;
                case 4:
                    scheduleInterview(sc);
                    break;
                case 5:
                    viewScheduledInterviews();
                    break;
                case 6:
                    System.out.println("Logging out from Company Dashboard...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }

    public void postNewJob(Scanner sc) {
        System.out.println("Enter Job Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Job Description: ");
        String desc = sc.nextLine();
        System.out.println("Enter Salary: ");
        double salary = sc.nextDouble();
        System.out.println("Enter Total Openings: ");
        int openings = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Job Location: ");
        String location = sc.nextLine();
        System.out.println("Enter Job Type (Full-Time/Part-Time): ");
        String type = sc.nextLine();

        int jobId = 100 + postedJobs.size() + 1;
        Job job = new Job(jobId, title, desc, salary, openings, LocalDateTime.now(), LocalDateTime.now().plusDays(30), location, type);
        postedJobs.add(job);
        jobApplications.put(jobId, new ArrayList<>());

        System.out.println("Job posted successfully: " + job.getJobTitle());
    }

    public void viewPostedJobs() {
        if (postedJobs.isEmpty()) {
            System.out.println("No jobs posted yet.");
            return;
        }
        System.out.println("Posted Jobs:");
        for (Job job : postedJobs) {
            System.out.println(job);
            System.out.println("------------------------");
        }
    }

    public void viewApplications() {
        if (jobApplications.isEmpty()) {
            System.out.println("No applications yet.");
            return;
        }
        for (Job job : postedJobs) {
            List<Candidate> applicants = jobApplications.get(job.getJobId());
            if (applicants == null || applicants.isEmpty()) {
                System.out.println("No applications for job: " + job.getJobTitle());
            } else {
                System.out.println("Applications for job: " + job.getJobTitle());
                for (Candidate candidate : applicants) {
                    System.out.println(" - " + candidate.getFullName() + " | Email: " + candidate.getEmail());
                }
            }
            System.out.println("------------------------");
        }
    }

    public void scheduleInterview(Scanner sc) {
        if (jobApplications.isEmpty()) {
            System.out.println("No applications to schedule interviews for.");
            return;
        }
        System.out.print("Enter Job ID to schedule interview for: ");
        int jobId = sc.nextInt();
        sc.nextLine();

        List<Candidate> applicants = jobApplications.get(jobId);
        if (applicants == null || applicants.isEmpty()) {
            System.out.println("No candidates applied for this job.");
            return;
        }

        System.out.println("Available Candidates:");
        for (int i = 0; i < applicants.size(); i++) {
            System.out.println((i + 1) + ". " + applicants.get(i).getFullName());
        }

        System.out.print("Choose candidate (number): ");
        int candidateIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (candidateIndex < 0 || candidateIndex >= applicants.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Candidate selected = applicants.get(candidateIndex);
        Interview interview = new Random().nextBoolean() ? new TechnicalInterviewService() : new HRInterviewService();
        interview.setInterviewDatetime(LocalDateTime.now().plusDays(1));
        scheduledInterviews.put(selected.getCandidateId(), interview);

        System.out.println("Interview scheduled with " + selected.getFullName() + " on " + interview.getInterviewDatetime());
    }

    public void viewScheduledInterviews() {
        if (scheduledInterviews.isEmpty()) {
            System.out.println("No interviews scheduled yet.");
            return;
        }

        for (Map.Entry<Integer, Interview> entry : scheduledInterviews.entrySet()) {
            System.out.println("Candidate ID: " + entry.getKey());
            System.out.println("Interview Time: " + entry.getValue().getInterviewDatetime());
            System.out.println("Interview Type: " + entry.getValue().getClass().getSimpleName().replace("Service", ""));
            System.out.println("------------------------");
        }
    }

    // below code can be used for testing purpose
    public void receiveApplication(int jobId, Candidate candidate) {
        jobApplications.putIfAbsent(jobId, new ArrayList<>());
        jobApplications.get(jobId).add(candidate);
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
