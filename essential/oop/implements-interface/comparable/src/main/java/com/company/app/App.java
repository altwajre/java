package com.company.app;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class App
{
    // implement Comparable for sorting Salary
    static class Employee implements Comparable{
        int Id;
        String Name;
        double Salary;
        static int i;
        public Employee(String name, double salary){
            Id = i++;
            this.Name = name;
            this.Salary = salary;
        }
        public int compareTo(Object o) {
            if(this.Salary == ((Employee)o).Salary){
                return 0;
            }
            else if(this.Salary > ((Employee)o).Salary){
                return 1;
            }
            else{
                return -1;
            }
        }
        @Override
        public String toString(){
            return String.format("Employee - Id: %s, Name: %s, Salary: %s", Id, Name, Salary);
        }
    }
    public static void main( String[] args )
    {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Tom", 100));
        employees.add(new Employee("Dick", 80));
        employees.add(new Employee("Harry", 200));
        Collections.sort(employees);
        for(Employee employee : employees){
            System.out.println(employee);
        }
    }
}
