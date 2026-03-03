package com.payroll;

/**
 * UC2 - Custom Exception to handle SQL errors
 */
public class PayrollException extends Exception {

    public PayrollException(String message, Throwable cause) {
        super(message, cause);
    }
}