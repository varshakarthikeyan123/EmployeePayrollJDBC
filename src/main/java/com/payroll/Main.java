package com.payroll;

/**
 * Main class for UC5 testing
 */
public class Main {

    public static void main(String[] args) throws PayrollException {

        EmployeePayrollDBService service =
                new EmployeePayrollDBService();

        // Example: Retrieve employees between 2018 and today
        service.getEmployeesByDateRange("2018-01-01", "2025-12-31");
    }
}