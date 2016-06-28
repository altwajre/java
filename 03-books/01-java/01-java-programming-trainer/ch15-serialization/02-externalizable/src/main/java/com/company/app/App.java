package com.company.app;

import java.io.*;
import java.util.Date;

class Employee implements Externalizable {
    String lName;
    String fName;
    String address;
    Date hireDate;
    int id;
    double salary;

    @Override
    public void writeExternal(ObjectOutput stream) throws IOException {
        stream.writeDouble(salary);
        stream.writeInt(id);
    }

    @Override
    public void readExternal(ObjectInput stream) throws IOException, ClassNotFoundException {
        salary = stream.readDouble();
        id = stream.readInt();
    }
}

public class App {
    public static void main(String[] args) {
        writeExternalTest();

        readExternalTest();
    }

    private static void readExternalTest() {
        System.out.println("# readExternalTest");
        try(FileInputStream fIn = new FileInputStream("NewEmployee.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn)) {
            Employee employee = new Employee();
            employee.readExternal(oIn);
            System.out.println("Deserialized employee with id " + employee.id);
            System.out.printf("salary = $%7.2f", employee.salary);
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeExternalTest() {
        System.out.println("# writeExternalTest");
        Employee employee = new Employee();
        employee.fName = "John";
        employee.lName = "Smith";
        employee.salary = 50000;
        employee.address = "12 main street";
        employee.hireDate = new Date();
        employee.id = 123;

        try (FileOutputStream fOut = new FileOutputStream("NewEmployee.ser");
             ObjectOutputStream oOut = new ObjectOutputStream(fOut)) {
            employee.writeExternal(oOut);  // externalizing employee
            System.out.println("An employee is externalized into NewEmployee.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
