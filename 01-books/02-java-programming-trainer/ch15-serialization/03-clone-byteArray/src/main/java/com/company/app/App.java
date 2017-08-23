package com.company.app;

import java.io.*;

class Employee implements Serializable {
    String lName;
    String fName;
    double salary;
}

public class App
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        Employee employee = new Employee();
        employee.lName = "John";
        employee.fName = "Smith";
        employee.salary = 50000;

        // Serialize into byte array
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        ObjectOutputStream oOut = new ObjectOutputStream(baOut);
        oOut.writeObject(employee);

        // Deserialize from byte array to clone the object
        ByteArrayInputStream baIn = new ByteArrayInputStream(baOut.toByteArray());
        ObjectInputStream oIn = new ObjectInputStream(baIn);
        Employee clonedEmployee = (Employee) oIn.readObject();
        System.out.println("# Deserialized");
        System.out.println("Last Name: "+clonedEmployee.lName);
        System.out.println("First Name: "+clonedEmployee.fName);
        System.out.println("Salary: "+clonedEmployee.salary);
    }
}
/*
output:
# Deserialized
Last Name: John
First Name: Smith
Salary: 50000.0
 */