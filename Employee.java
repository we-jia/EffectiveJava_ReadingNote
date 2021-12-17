package com.company;

import com.company.annualSalary.CalculateAnnualSalary;
import com.company.annualSalary.JuniorAnnualSalary;
import com.company.annualSalary.MiddleAnnualSalary;
import com.company.annualSalary.SeniorAnnualSalary;
import lombok.Data;

@Data
public class Employee {
    /**
     * 員工姓名
     */
    private String name;

    /**
     * 烤雞
     */
    private int friedChicken;

    /**
     * 薪酬
     */
    private int salary;

    /**
     * 職等
     */
    private Level level;

    /**
     * 計算一整年薪酬
     */
    private CalculateAnnualSalary calculateAnnualSalary;

    public void setLevel(Level level) {
        this.level = level;
        switch(level){
            case JUNIOR:
                this.calculateAnnualSalary = new JuniorAnnualSalary(this);
                return;
            case MIDDLE:
                this.calculateAnnualSalary = new MiddleAnnualSalary(this);
                return;
            case SENIOR:
                this.calculateAnnualSalary = new SeniorAnnualSalary(this);
                return;
            default:
        }
    }
}
