package com.company.app;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

class Address {
    private String street;
    private String city;
    private int zipcode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString(){
        return getStreet() + ", " + getCity() + ", " + getZipcode();
    }
}
class Employee {
    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private long[] phoneNumbers;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(long[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Employee Details *****\n");
        sb.append("ID="+getId()+"\n");
        sb.append("Name="+getName()+"\n");
        sb.append("Permanent="+isPermanent()+"\n");
        sb.append("Role="+getRole()+"\n");
        sb.append("Phone Numbers="+ Arrays.toString(getPhoneNumbers())+"\n");
        sb.append("Address="+getAddress());
        sb.append("\n*****************************");

        return sb.toString();
    }
}
public class App
{
    public static void main( String[] args ) throws FileNotFoundException {
        Employee employee = createEmployee();

        JsonObjectBuilder employeeBuilder = Json.createObjectBuilder();
        JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
        JsonArrayBuilder phoneNumBuilder = Json.createArrayBuilder();

        for(long phone : employee.getPhoneNumbers()){
            phoneNumBuilder.add(phone);
        }

        addressBuilder.add("street", employee.getAddress().getStreet())
                .add("city", employee.getAddress().getCity())
                .add("zipcode", employee.getAddress().getZipcode());

        employeeBuilder.add("id", employee.getId())
                .add("name", employee.getName())
                .add("role", employee.getRole());

        employeeBuilder.add("phoneNumbers", phoneNumBuilder);
        employeeBuilder.add("address", addressBuilder);

        JsonObject empJsonObject = employeeBuilder.build();

        System.out.println("Employee JSON String\n" + empJsonObject);

        // write to file
        OutputStream outputStream = new FileOutputStream("temp.txt");
        JsonWriter jsonWriter = Json.createWriter(outputStream);

        /*
        We can get JsonWriter from JsonWriterFactory also
        JsonWriterFactory factory = Json.createWriterFactory(null);
		jsonWriter = factory.createWriter(outputStream);
         */
        jsonWriter.writeObject(empJsonObject);
        jsonWriter.close();
    }

    static Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId(100);
        employee.setName("David");
        employee.setPermanent(false);
        employee.setPhoneNumbers(new long[] {123456, 987654});
        employee.setRole("Manager");

        Address address = new Address();
        address.setCity("Bangalore");
        address.setStreet("BTM 1st Stage");
        address.setZipcode(560100);

        employee.setAddress(address);
        return employee;
    }
}
