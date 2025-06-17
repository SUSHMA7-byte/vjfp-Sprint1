package com.sprint1.dao;

import com.sprint1.service.user.CandidateUser;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateUserDAOImpl implements CandidateUserDAO {

    @Override
    public void addCandidate(CandidateUser candidate) {
        String sql = "INSERT INTO candidate_user (full_name, email, phone_number, resume_link, college_name) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println("Enter your full name");
            stmt.setString(1, candidate.getFullName());
            System.out.println("Enter your email");
            stmt.setString(2, candidate.getEmail());
            System.out.println("Enter your phone number");
            stmt.setString(3, candidate.getPhoneNumber());
            System.out.println("Enter your resume link");
            stmt.setString(4, candidate.getResumeLink());
            System.out.println("Enter your college name");
            stmt.setString(5, candidate.getCollegeName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CandidateUser getCandidateByEmail(String email) {
        String sql = "SELECT * FROM candidate_user WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new CandidateUser(
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("resume_link"),
                        rs.getString("college_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<CandidateUser> getAllCandidates() {
        List<CandidateUser> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidate_user";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                candidates.add(new CandidateUser(
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("resume_link"),
                        rs.getString("college_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidates;
    }

    @Override
    public boolean updateCandidate(CandidateUser candidate) {
        String sql = "UPDATE candidate_user SET full_name = ?, phone_number = ?, resume_link = ?, college_name = ? WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, candidate.getFullName());
            stmt.setString(2, candidate.getPhoneNumber());
            stmt.setString(3, candidate.getResumeLink());
            stmt.setString(4, candidate.getCollegeName());
            stmt.setString(5, candidate.getEmail());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCandidateByEmail(String email) {
        String sql = "DELETE FROM candidate_user WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
