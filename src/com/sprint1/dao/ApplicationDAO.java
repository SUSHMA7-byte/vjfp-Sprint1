package com.sprint1.dao;

import com.sprint1.model.Application;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // Apply a candidate to a job
    public void applyToJob(String candidateId, String jobId, String status) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all applications for a given jobId
    public List<Application> getApplicationsByJobId(String jobId) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
