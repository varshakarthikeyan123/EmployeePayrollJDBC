package com.payroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    /**
     * UC2 - Retrieve Employee Payroll Data from Database
     */
    public List<EmployeePayrollData> readData() throws PayrollException {

        List<EmployeePayrollData> employeeList = new ArrayList<>();

        String query = "SELECT id, name, salary FROM employee_payroll";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Iterate through result set
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");

                EmployeePayrollData employee =
                        new EmployeePayrollData(id, name, salary);

                employeeList.add(employee);
            }

        } catch (SQLException e) {
            throw new PayrollException("Error retrieving employee payroll data", e);
        }

        return employeeList;
        }
}