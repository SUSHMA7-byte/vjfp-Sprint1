package com.sprint1.dao;

import com.sprint1.model.Job;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public void postJob(Job job) {
        String sql = "INSERT INTO Job (job_id, job_title, job_description, salary_package, total_openings, application_start_date, application_end_date, job_location, job_type, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, job.getJobId());
            ps.setString(2, job.getJobTitle());
            ps.setString(3, job.getJobDescription());
            ps.setDouble(4, job.getSalaryPackage());
            ps.setInt(5, job.getTotalOpenings());
            ps.setDate(6, Date.valueOf(job.getApplicationStartDate()));
            ps.setDate(7, Date.valueOf(job.getApplicationEndDate()));
            ps.setString(8, job.getJobLocation());
            ps.setString(9, job.getJobType());
            ps.setString(10, job.getCompanyId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Job> getJobsByCompanyId(String companyId) {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Job WHERE company_id = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getString("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setJobDescription(rs.getString("job_description"));
                job.setSalaryPackage(rs.getDouble("salary_package"));
                job.setTotalOpenings(rs.getInt("total_openings"));
                job.setApplicationStartDate(rs.getDate("application_start_date").toLocalDate());
                job.setApplicationEndDate(rs.getDate("application_end_date").toLocalDate());
                job.setJobLocation(rs.getString("job_location"));
                job.setJobType(rs.getString("job_type"));
                job.setCompanyId(rs.getString("company_id"));
                jobs.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }

    public List<Job> getAllActiveJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Job WHERE application_end_date >= CURDATE()";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getString("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setJobDescription(rs.getString("job_description"));
                job.setSalaryPackage(rs.getDouble("salary_package"));
                job.setTotalOpenings(rs.getInt("total_openings"));
                job.setApplicationStartDate(rs.getDate("application_start_date").toLocalDate());
                job.setApplicationEndDate(rs.getDate("application_end_date").toLocalDate());
                job.setJobLocation(rs.getString("job_location"));
                job.setJobType(rs.getString("job_type"));
                job.setCompanyId(rs.getString("company_id"));
                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }

    public Job getJobById(String jobId) {
        String sql = "SELECT * FROM Job WHERE job_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jobId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Job job = new Job(
                        rs.getString("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_description"),
                        rs.getDouble("salary_package"),
                        rs.getInt("total_openings"),
                        rs.getDate("application_start_date").toLocalDate(),
                        rs.getDate("application_end_date").toLocalDate(),
                        rs.getString("job_location"),
                        rs.getString("job_type")
                );
                job.setCompanyId(rs.getString("company_id"));
                return job;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
