package com.payroll;

/**
 * Main class for UC4 testing
 */
public class Main {

    public static void main(String[] args) throws PayrollException {

        EmployeePayrollDBService service =
                new EmployeePayrollDBService();

        // Update salary of June safely
        service.updateSalaryUsingPreparedStatement("June", 8000000);

        // Display updated records
        service.readData().forEach(System.out::println);
    }
}