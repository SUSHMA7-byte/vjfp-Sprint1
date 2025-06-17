package com.sprint1.dao;

import com.sprint1.model.Interview;
import com.sprint1.service.interview.HRInterviewService;
import com.sprint1.service.interview.TechnicalInterviewService;
import com.sprint1.util.DBUtil;
import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

public class InterviewDAO {

    // Save interview to DB
    public void saveInterview(int candidateId, Interview interview) {
        String sql = "INSERT INTO Interview (candidate_id, interview_datetime, feedback, result_status, interview_type) VALUES (?, ?, ?, ?, ?)";
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
                String generatedId = rs.getString(1);
                interview.setInterviewId(generatedId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch interview by interviewId
    public Interview getInterviewById(String interviewId) {
        String sql = "SELECT * FROM Interview WHERE interview_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, interviewId);
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateFeedback(int interviewId, String feedback, String resultStatus) {
        String sql = "UPDATE Interview SET feedback = ?, result_status = ? WHERE interview_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, feedback);
            ps.setString(2, resultStatus);
            ps.setInt(3, interviewId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete interview
    public void deleteInterview(int interviewId) {
        String sql = "DELETE FROM Interview WHERE interview_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, interviewId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Interview> getInterviewsByCandidateId(String candidateId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM Interview WHERE candidate_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, candidateId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String interviewId = rs.getString("interview_id");
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

                interviews.add(interview);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return interviews;
    }

    public void scheduleInterview(Interview interview) {
        String sql = "INSERT INTO Interview (candidate_id, job_id, company_id, interview_datetime, feedback, result_status, interview_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, interview.getCandidateId());
            ps.setString(2, interview.getJobId());
            ps.setString(3, interview.getCompanyId());
            ps.setTimestamp(4, Timestamp.valueOf(interview.getInterviewDatetime()));
            ps.setString(5, interview.getFeedback());
            ps.setString(6, interview.getResultStatus());

            String type = (interview instanceof HRInterviewService) ? "HR" : "Technical";
            ps.setString(7, type);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                interview.setInterviewId(rs.getString(1));
            }

            System.out.println("Interview scheduled successfully with ID: " + interview.getInterviewId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Interview> getInterviewsByCompany(String companyId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM Interview WHERE company_id = ? ORDER BY interview_datetime DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Interview interview;
                String type = rs.getString("interview_type");

                if ("HR".equalsIgnoreCase(type)) {
                    interview = new HRInterviewService();
                } else {
                    interview = new TechnicalInterviewService();
                }

                interview.setInterviewId(rs.getString("interview_id"));
                interview.setCandidateId(rs.getString("candidate_id"));
                interview.setJobId(rs.getString("job_id"));
                interview.setCompanyId(rs.getString("company_id"));
                interview.setInterviewDatetime(rs.getTimestamp("interview_datetime").toLocalDateTime());
                interview.setFeedback(rs.getString("feedback"));
                interview.setResultStatus(rs.getString("result_status"));

                interviews.add(interview);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return interviews;
    }
}