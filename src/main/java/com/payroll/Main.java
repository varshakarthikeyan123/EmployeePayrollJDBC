package com.payroll;

/**
 * Main class for UC6 testing
 */
public class Main {

    public static void main(String[] args) throws PayrollException {

        EmployeePayrollDBService service =
                new EmployeePayrollDBService();

        service.getSalaryStatisticsByGender();
    }
}