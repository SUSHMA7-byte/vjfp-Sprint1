package com.sprint1.dao;

import com.sprint1.model.Interview;
import com.sprint1.service.interview.HRInterviewService;
import com.sprint1.service.interview.TechnicalInterviewService;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;

public class InterviewDAO {

    // Save interview to DB
    public void saveInterview(int candidateId, Interview interview) {
        String sql = "INSERT INTO interviews (candidate_id, interview_datetime, feedback, result_status, interview_type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, candidateId);
            ps.setTimestamp(2, Timestamp.valueOf(interview.getInterviewDatetime()));
            ps.setString(3, interview.getFeedback());
            ps.setString(4, interview.getResultStatus());
            String type = (interview instanceof HRInterviewService) ? "HR" : "Technical";
            ps.setString(5, type);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                interview.setInterviewId(generatedId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch interview by interviewId
    public Interview getInterviewById(int interviewId) {
        String sql = "SELECT * FROM interviews WHERE interview_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, interviewId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LocalDateTime datetime = rs.getTimestamp("interview_datetime").toLocalDateTime();
                String feedback = rs.getString("feedback");
                String resultStatus = rs.getString("result_status");
                String type = rs.getString("interview_type");

                Interview interview;
                if ("HR".equalsIgnoreCase(type)) {
                    interview = new HRInterviewService();
                } else {
                    interview = new TechnicalInterviewService();
                }

                interview.setInterviewId(interviewId);
                interview.setInterviewDatetime(datetime);
                interview.setFeedback(feedback);
                interview.setResultStatus(resultStatus);

                return interview;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update interview feedback/result
    public void updateFeedback(int interviewId, String feedback, String resultStatus) {
        String sql = "UPDATE interviews SET feedback = ?, result_status = ? WHERE interview_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, feedback);
            ps.setString(2, resultStatus);
            ps.setInt(3, interviewId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete interview
    public void deleteInterview(int interviewId) {
        String sql = "DELETE FROM interviews WHERE interview_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, interviewId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
