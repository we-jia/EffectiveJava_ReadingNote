package com.company.annualSalary;

import com.company.Employee;

public class MiddleAnnualSalary extends CalculateAnnualSalary {
    public MiddleAnnualSalary(Employee employee) {
        super(employee);
    }

    @Override
    protected void calculate() {
        int chicken = this.employee.getFriedChicken();

        int baseAnnualSalary = this.employee.getSalary() * 12;
        int annualSalary = this.employee.getSalary() * chicken * 2 + baseAnnualSalary;

        System.out.println("name : " + this.employee.getName() + ", salary : " + annualSalary);
    }
}
