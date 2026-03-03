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

    /**
     * UC3 - Update Employee Salary using Statement
     * This method updates salary of an employee based on name
     */
    public void updateSalary(String name, double salary) throws PayrollException {

        // SQL query to update salary
        String query = "UPDATE employee_payroll SET salary = "
                + salary + " WHERE name = '" + name + "'";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // Execute update query
            int rowsAffected = statement.executeUpdate(query);

            // Check if update happened
            if (rowsAffected > 0) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            throw new PayrollException("Error updating salary using Statement", e);
        }
    }
    /**
     * UC4 - Update Employee Salary using PreparedStatement
     * This method prevents SQL Injection and is safer.
     */
    public void updateSalaryUsingPreparedStatement(String name, double salary)
            throws PayrollException {

        // SQL query using placeholders
        String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            // Set values for placeholders
            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2, name);

            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Salary updated successfully using PreparedStatement!");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            throw new PayrollException(
                    "Error updating salary using PreparedStatement", e);
        }
    }
    /**
     * UC5 - Retrieve Employees whose start_date
     * falls between given date range
     */
    public void getEmployeesByDateRange(String startDate, String endDate)
            throws PayrollException {

        // SQL query using BETWEEN
        String query = "SELECT * FROM employee_payroll " +
                "WHERE start_date BETWEEN ? AND ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            // Set date parameters
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Employees between " + startDate +
                    " and " + endDate + ":");

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + " " +
                                resultSet.getString("name") + " " +
                                resultSet.getDouble("salary") + " " +
                                resultSet.getDate("start_date")
                );
            }

        } catch (SQLException e) {
            throw new PayrollException(
                    "Error retrieving employees by date range", e);
        }
    }
    /**
     * UC6 - Perform Aggregate Functions
     * Calculates SUM, AVG and COUNT of salary grouped by gender
     */
    public void getSalaryStatisticsByGender() throws PayrollException {

        // SQL query with aggregate functions
        String query = "SELECT gender, SUM(salary) AS totalSalary, " +
                "AVG(salary) AS averageSalary, " +
                "COUNT(*) AS employeeCount " +
                "FROM employee_payroll GROUP BY gender";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("Salary Statistics Based On Gender:");
            System.out.println("-----------------------------------");

            while (resultSet.next()) {

                String gender = resultSet.getString("gender");
                double totalSalary = resultSet.getDouble("totalSalary");
                double averageSalary = resultSet.getDouble("averageSalary");
                int count = resultSet.getInt("employeeCount");

                System.out.println("Gender: " + gender);
                System.out.println("Total Salary: " + totalSalary);
                System.out.println("Average Salary: " + averageSalary);
                System.out.println("Employee Count: " + count);
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            throw new PayrollException(
                    "Error retrieving aggregate salary statistics", e);
        }
    }
}