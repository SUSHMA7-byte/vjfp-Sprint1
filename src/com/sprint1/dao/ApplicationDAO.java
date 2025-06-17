package com.sprint1.dao;

import com.sprint1.model.Application;
import com.sprint1.model.Candidate;
import com.sprint1.model.Job;
import com.sprint1.util.DBUtil;
import com.sprint1.model.Job;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ApplicationDAO {

    // Apply a candidate to a job
    public void applyForJob(String candidateId, String jobId, String status) {
        String sql = "INSERT INTO Application (application_id, candidate_id, job_id, application_status, application_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String applicationId = "APP" + System.currentTimeMillis(); // Generate simple unique ID
            ps.setString(1, applicationId);
            ps.setString(2, candidateId);
            ps.setString(3, jobId);
            ps.setString(4, status);
            ps.setDate(5, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();

            System.out.println("Application submitted successfully with ID: " + applicationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve all applications for a given jobId
    public List<Application> getApplicationsForJob(String jobId) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT application_id, application_status, application_date FROM Application WHERE job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Application app = new Application(
                        rs.getString("application_id"),
                        rs.getString("application_status"),
                        rs.getDate("application_date").toLocalDate().atStartOfDay()
                );
                list.add(app);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean checkApplicationExists(String candidateId, String jobId) {
        String sql = "SELECT COUNT(*) FROM Application WHERE candidate_id = ? AND job_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, candidateId);
            ps.setString(2, jobId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Job> getJobsAppliedByCandidate(String candidateId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT j.* FROM Job j JOIN Application a ON j.job_id = a.job_id WHERE a.candidate_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, candidateId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job(
                        rs.getString("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_description"),
                        rs.getDouble("salary_package"),
                        rs.getInt("total_openings"),
                        rs.getDate("application_start_date").toLocalDate().atStartOfDay(),
                        rs.getDate("application_end_date").toLocalDate().atStartOfDay(),
                        rs.getString("job_location"),
                        rs.getString("job_type")
                );
                job.setCompanyId(rs.getString("company_id"));
                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }

        public Map<Job, List<Candidate>> getApplicationsByCompany(String companyId) {
            Map<Job, List<Candidate>> applicationsMap = new HashMap<>();

            String sql = "SELECT j.*, c.* FROM Application a " +
                    "JOIN Job j ON a.job_id = j.job_id " +
                    "JOIN Candidate c ON a.candidate_id = c.candidate_id " +
                    "WHERE j.company_id = ?";

            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, companyId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Job job = new Job(
                            rs.getString("job_id"),
                            rs.getString("job_title"),
                            rs.getString("job_description"),
                            rs.getDouble("salary_package"),
                            rs.getInt("total_openings"),
                            rs.getDate("application_start_date").toLocalDate().atStartOfDay(),
                            rs.getDate("application_end_date").toLocalDate().atStartOfDay(),
                            rs.getString("job_location"),
                            rs.getString("job_type")
                    );
                    job.setCompanyId(rs.getString("company_id"));


                    Candidate candidate = new Candidate();
                    candidate.setCandidateId(rs.getString("candidate_id"));
                    candidate.setFullName(rs.getString("full_name"));
                    candidate.setEmail(rs.getString("email"));
                    candidate.setPhoneNumber(rs.getString("phone_number"));
                    candidate.setResumeLink(rs.getString("resume_link"));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return applicationsMap;
        }

    public List<Candidate> getApplicantsForJob(String jobId) {
        List<Candidate> applicants = new ArrayList<>();
        String sql = "SELECT c.* FROM Candidate c " +
                "JOIN Application a ON c.candidate_id = a.candidate_id " +
                "WHERE a.job_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setCandidateId(rs.getString("candidate_id"));
                candidate.setFullName(rs.getString("full_name"));
                candidate.setEmail(rs.getString("email"));
                candidate.setPhoneNumber(rs.getString("phone_number"));
                candidate.setResumeLink(rs.getString("resume_link"));
                candidate.setCollege(rs.getString("college"));
                candidate.setCountry(rs.getString("country"));
                // optionally skip passwordHash for privacy
                applicants.add(candidate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicants;
    }

}

