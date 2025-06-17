package com.sprint1.dao;

import com.sprint1.model.Employee;
import com.sprint1.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee emp, String companyId) {
        String sql = "INSERT INTO Employee (employee_id, full_name, role, contact_number, email, company_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getEmployeeId());
            ps.setString(2, emp.getFullName());
            ps.setString(3, emp.getRole());
            ps.setString(4, emp.getContactNumber());
            ps.setString(5, emp.getEmail());
            ps.setString(6, companyId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int empId) {
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getString("employee_id"),
                        rs.getString("full_name"),
                        rs.getString("role"),
                        rs.getString("contact_number"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployeesByCompanyId(String companyId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE company_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getString("employee_id"),
                        rs.getString("full_name"),
                        rs.getString("role"),
                        rs.getString("contact_number"),
                        rs.getString("email")
                );
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteEmployee(int empId) {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}