package com.payroll;

/**
 * Main class for UC3 testing
 */
public class Main {

    public static void main(String[] args) throws PayrollException {

        EmployeePayrollDBService service =
                new EmployeePayrollDBService();

        // Update salary of Bill
        service.updateSalary("Bill", 7000000);

        // Display updated data
        service.readData().forEach(System.out::println);
    }
}