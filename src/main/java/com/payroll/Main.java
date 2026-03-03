package com.payroll;

/**
 * Main class for UC7 testing
 */
public class Main {

    public static void main(String[] args) throws PayrollException {

        EmployeePayrollDBService service =
                new EmployeePayrollDBService();

        EmployeePayrollData employee =
                service.addEmployee("Terisa",
                        3000000,
                        "2026-03-03",
                        "F");

        System.out.println(employee);
    }
}