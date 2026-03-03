package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * UC1 - Establish Payroll Database Connection
 */
public class EmployeePayrollDBService {

    private static final String URL =
            "jdbc:mysql://localhost:3306/payroll_service";

    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void testConnection() {
        try (Connection connection = getConnection()) {
            System.out.println("Connection Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}