package com.company.app;

import java.io.*;

class Employee implements Serializable{
    String lName;
    String fName;
    double salary;
}
public class App
{
    public static void main( String[] args )
    {
        fileOutputStreamTest();

        fileInputStreamTest();
    }

    private static void fileInputStreamTest() {
        System.out.println("# fileInputStreamTest");
        try(FileInputStream fIn = new FileInputStream("BestEmployee.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn)){
            Employee employee = (Employee) oIn.readObject();
            System.out.println("Last Name: "+employee.lName);
            System.out.println("First Name: "+employee.fName);
            System.out.println("Salary: "+employee.salary);
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void fileOutputStreamTest() {
        System.out.println("# fileOutputStreamTest");
        Employee employee = new Employee();
        employee.lName = "John";
        employee.fName = "Smith";
        employee.salary = 50000;

        try(FileOutputStream fOut = new FileOutputStream("BestEmployee.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut)){
            oOut.writeObject(employee); // serializing employee
        }
        catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Employee object has been serialized into BestEmployee.ser");
    }
}
/*
output:
# fileOutputStreamTest
Employee object has been serialized into BestEmployee.ser
# fileInputStreamTest
Last Name: John
First Name: Smith
Salary: 50000.0
 */
