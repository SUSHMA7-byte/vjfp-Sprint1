package com.sprint1.service.job;

import com.sprint1.model.Candidate;
import com.sprint1.model.Job;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobServiceImpl implements JobService {

    @Override
    public void postJob(Job job) {
        String sql = "INSERT INTO jobs (job_id, title, description, salary, total_openings, application_start_date, application_end_date, location, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.println("Enter Job ID");
            ps.setInt(1, job.getJobId());
            System.out.println("Enter Job Title");
            ps.setString(2, job.getJobTitle());
            System.out.println("Enter Job Description");
            ps.setString(3, job.getJobDescription());
            System.out.println("Enter Job Salary");
            ps.setDouble(4, job.getSalary());
            System.out.println("Enter Job Total Openings");
            ps.setInt(5, job.getTotalOpenings());
            ps.setTimestamp(6, Timestamp.valueOf(job.getApplication_start_date()));
            ps.setTimestamp(7, Timestamp.valueOf(job.getApplication_end_date()));
            ps.setString(8, job.getJobLocation());
            ps.setString(9, job.getJobType());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Job posted successfully!");
            } else {
                System.out.println("❌ Failed to post job.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Job> getAllJobsFromDB() {
        List<Job> jobList = new ArrayList<>();
        String sql = "SELECT * FROM jobs";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getInt("total_openings"),
                        rs.getTimestamp("application_start_date").toLocalDateTime(),
                        rs.getTimestamp("application_end_date").toLocalDateTime(),
                        rs.getString("location"),
                        rs.getString("type")
                );
                jobList.add(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobList;
    }

    @Override
    public void displayJobDetails(Job job) {
        System.out.println(job);  // Uses Job's toString()
    }

    @Override
    public void applyForJob(Candidate candidate, String jobId) {
        String sql = "INSERT INTO applications (candidate_id, job_id, applied_at) VALUES (?, ?, NOW())";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidate.getCandidateId());
            ps.setInt(2, Integer.parseInt(jobId));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ " + candidate.getFullName() + " applied for job ID " + jobId);
            } else {
                System.out.println("❌ Application failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
