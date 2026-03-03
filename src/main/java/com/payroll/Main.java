package com.payroll;

import java.util.List;

/**
 * Main class for UC2 testing
 */
public class Main {

    public static void main(String[] args) throws PayrollException {

        EmployeePayrollDBService service =
                new EmployeePayrollDBService();

        List<EmployeePayrollData> employees = service.readData();

        // Print employee list
        employees.forEach(System.out::println);
    }
}