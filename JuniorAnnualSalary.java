package com.company.annualSalary;

import com.company.Employee;

public class JuniorAnnualSalary extends CalculateAnnualSalary {
    public JuniorAnnualSalary(Employee employee) {
        super(employee);
    }

    @Override
    protected void calculate() {
        int chicken = this.employee.getFriedChicken();

        int baseAnnualSalary = this.employee.getSalary() * 12;
        int annualSalary = this.employee.getSalary() * chicken + baseAnnualSalary;

        System.out.println("name : " + this.employee.getName() + ", salary : " + annualSalary);
    }
}
