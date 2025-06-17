package com.sprint1.dao;

import com.sprint1.model.Job;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    public void postJob(Job job, int companyId) {
        String sql = "INSERT INTO jobs (job_title, job_description, salary, total_openings, application_start_date, application_end_date, job_location, job_type, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, job.getJobTitle());
            ps.setString(2, job.getJobDescription());
            ps.setDouble(3, job.getSalary());
            ps.setInt(4, job.getTotalOpenings());
            ps.setTimestamp(5, Timestamp.valueOf(job.getApplication_start_date()));
            ps.setTimestamp(6, Timestamp.valueOf(job.getApplication_end_date()));
            ps.setString(7, job.getJobLocation());
            ps.setString(8, job.getJobType());
            ps.setInt(9, companyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Job> getAllJobsByCompanyId(int companyId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE company_id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_description"),
                        rs.getDouble("salary"),
                        rs.getInt("total_openings"),
                        rs.getTimestamp("application_start_date").toLocalDateTime(),
                        rs.getTimestamp("application_end_date").toLocalDateTime(),
                        rs.getString("job_location"),
                        rs.getString("job_type")
                );
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
