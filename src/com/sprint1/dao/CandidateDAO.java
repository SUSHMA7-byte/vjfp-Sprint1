package com.sprint1.dao;

import com.sprint1.model.Candidate;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

    public int registerCandidate(Candidate candidate) {
        String sql = "INSERT INTO Candidate (name, email, phone, resume_link, college_name, country) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, candidate.getFullName());
            ps.setString(2, candidate.getEmail());
            ps.setString(3, candidate.getPhoneNumber());
            ps.setString(4, candidate.getResumeLink());
            ps.setString(5, candidate.getCollegeName());
            ps.setString(6, candidate.getCountry());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public Candidate getCandidateByEmail(String email) {
        String sql = "SELECT * FROM Candidate WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Candidate(
                        rs.getInt("candidate_id"),
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
                        rs.getInt("candidate_id"),
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
