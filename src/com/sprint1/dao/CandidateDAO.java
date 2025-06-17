package com.sprint1.dao;

import com.sprint1.model.Candidate;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CandidateDAO {

    public void registerCandidate(Candidate candidate) {
        String sql = "INSERT INTO Candidate (candidate_id, name, email, phone, resume_link, college_name, country) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String generatedId = UUID.randomUUID().toString();
            candidate.setCandidateId(generatedId);

            ps.setString(1, candidate.getCandidateId());
            ps.setString(2, candidate.getFullName());
            ps.setString(3, candidate.getEmail());
            ps.setString(4, candidate.getPhoneNumber());
            ps.setString(5, candidate.getResumeLink());
            ps.setString(6, candidate.getCollegeName());
            ps.setString(7, candidate.getCountry());

            ps.executeUpdate();
            System.out.println("Registration successful. Your Candidate ID is: " + candidate.getCandidateId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Candidate getCandidateById(String candidateId) {
        String sql = "SELECT * FROM Candidate WHERE candidate_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, candidateId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Candidate(
                        rs.getString("candidate_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("resume_link"),
                        rs.getString("college_name"),
                        rs.getString("country")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Candidate getCandidateByEmail(String email) {
        String sql = "SELECT * FROM Candidate WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Candidate(
                        rs.getString("candidate_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("resume_link"),
                        rs.getString("college_name"),
                        rs.getString("country")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM Candidate";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getString("candidate_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("resume_link"),
                        rs.getString("college_name"),
                        rs.getString("country")
                );
                candidates.add(candidate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidates;
    }

    public boolean updateCandidate(Candidate candidate) {
        String sql = "UPDATE Candidate SET name = ?, phone = ?, resume_link = ?, college_name = ?, country = ? WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, candidate.getFullName());
            stmt.setString(2, candidate.getPhoneNumber());
            stmt.setString(3, candidate.getResumeLink());
            stmt.setString(4, candidate.getCollegeName());
            stmt.setString(5, candidate.getCountry());
            stmt.setString(6, candidate.getEmail());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCandidateByEmail(String email) {
        String sql = "DELETE FROM Candidate WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
