package com.company.annualSalary;

import com.company.Employee;

public abstract class CalculateAnnualSalary {
    Employee employee;

    protected CalculateAnnualSalary(Employee employee) {
        this.employee = employee;
    }

    public void execute() {
        logBefore();
        calculate();
        logAfter();
    }

    protected abstract void calculate();

    private void logBefore() {
        System.out.println("log:Before");
    }

    private void logAfter() {
        System.out.println("log:After");
    }
}
