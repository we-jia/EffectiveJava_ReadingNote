package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // mock data
        List<Employee> list = new ArrayList<>();
        Employee toast = new Employee();
        toast.setName("吐司");
        toast.setSalary(50000);
        toast.setLevel(Level.MIDDLE);
        toast.setFriedChicken(5);

        Employee moonTea = new Employee();
        moonTea.setName("狗毛");
        moonTea.setSalary(60000);
        moonTea.setLevel(Level.SENIOR);
        moonTea.setFriedChicken(5);

        Employee json = new Employee();
        json.setName("尾家");
        json.setSalary(40000);
        json.setLevel(Level.JUNIOR);
        json.setFriedChicken(1);

        list.add(toast);
        list.add(moonTea);
        list.add(json);

        for(Employee e : list){
            e.getCalculateAnnualSalary().execute();
            System.out.println();
        }
    }
}
