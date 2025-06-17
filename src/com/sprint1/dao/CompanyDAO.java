package com.sprint1.dao;

import com.sprint1.model.Company;
import com.sprint1.util.DBUtil;

import java.sql.*;

public class CompanyDAO {

    public Company getCompanyById(int companyId) {
        Company company = null;
        String query = "SELECT * FROM Company WHERE company_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                company = new Company();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setIndustryType(rs.getString("industry_type"));
                company.setContactEmail(rs.getString("contact_email"));
                // Add more fields if present
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }

    public Company getCompanyByEmail(String email) {
        Company company = null;
        String query = "SELECT * FROM Company WHERE contact_email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                company = new Company();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setIndustryType(rs.getString("industry_type"));
                company.setContactEmail(rs.getString("contact_email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return company;
    }


    public int insertCompany(Company company) {
        String query = "INSERT INTO Company (company_name, industry_type, contact_email) VALUES (?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, company.getCompanyName());
            ps.setString(2, company.getIndustryType());
            ps.setString(3, company.getContactEmail());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    company.setCompanyId(generatedId); // optional
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public boolean validateCompanyLogin(int companyId, String email) {
        String query = "SELECT * FROM Company WHERE company_id = ? AND contact_email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, companyId);
            ps.setString(2, email);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
