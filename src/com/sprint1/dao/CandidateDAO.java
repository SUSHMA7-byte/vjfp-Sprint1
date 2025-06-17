package com.sprint1.dao;

import com.sprint1.model.Candidate;
import com.sprint1.util.DBUtil;

import java.sql.*;

public class CandidateDAO {

    // Fetch candidate from DB using ID
    public Candidate getCandidateById(int candidateId) {
        String sql = "SELECT * FROM Candidate WHERE candidate_id = ?";
        Candidate candidate = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidateId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                candidate = new Candidate(
                        rs.getInt("candidate_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("resume_link"),
                        rs.getString("college"),
                        rs.getString("country")
                );
                candidate.setPasswordHash(rs.getString("password_hash")); // Optional
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidate;
    }

    // Insert new candidate (e.g., during registration)
    public boolean insertCandidate(Candidate candidate) {
        String sql = "INSERT INTO candidates (candidate_id, full_name, email, phone_number, resume_link, college, country, password_hash) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidate.getCandidateId());
            ps.setString(2, Candidate.getFullName());
            ps.setString(3, candidate.getEmail());
            ps.setString(4, candidate.getPhoneNumber());
            ps.setString(5, candidate.getResumeLink());
            ps.setString(6, candidate.getCollege());
            ps.setString(8, candidate.getPasswordHash());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

