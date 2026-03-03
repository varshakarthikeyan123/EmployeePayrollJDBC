package com.payroll;

/**
 * Main class for UC1 testing
 */
public class Main {

    public static void main(String[] args) {

        EmployeePayrollDBService service =
                new EmployeePayrollDBService();

        service.testConnection();
    }
}