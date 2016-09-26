package com.company;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Employee> currentStaff = new ArrayList<>();

    public void assign(List<Employee> staff){
        for(Employee employee : staff){
            if(employee.isRetired()){
                throw new IllegalArgumentException("Cannot assign a retried person");
            }
            currentStaff.add(employee);
        }
    }

    public int manpowerCount(){
        return currentStaff.size();
    }
}
