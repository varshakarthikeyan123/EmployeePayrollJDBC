package com.payroll;

/**
 * Model class representing employee payroll data
 */
public class EmployeePayrollData {

    private int id;
    private String name;
    private double salary;

    // Constructor
    public EmployeePayrollData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // toString method for printing employee data
    @Override
    public String toString() {
        return id + " " + name + " " + salary;
    }
}