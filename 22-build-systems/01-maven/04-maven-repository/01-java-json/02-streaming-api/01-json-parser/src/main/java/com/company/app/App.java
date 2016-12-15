package com.company.app;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    static final String FILE_NAME = "employee.txt";
    public static void main( String[] args ) throws IOException {

        InputStream inputStream = new FileInputStream(FILE_NAME);

        JsonParser jsonParser = Json.createParser(inputStream);

        /*
        We can create JsonParser from JsonParserFactory also with below code
        JsonParserFactory factory = Json.createParserFactory(null);
		jsonParser = factory.createParser(inputStream);
         */

        Employee employee = new Employee();
        Address address = new Address();
        String keyName = null;
        List<Long> phoneNums = new ArrayList<>();

        while(jsonParser.hasNext()){
            Event event = jsonParser.next();
            switch (event){
                case KEY_NAME:
                    keyName = jsonParser.getString();
                    break;
                case VALUE_STRING:
                    setStringValues(employee, address, keyName, jsonParser.getString());
                    break;
                case VALUE_NUMBER:
                    setNumberValues(employee, address, keyName, jsonParser.getLong(), phoneNums);
                    break;
                case VALUE_FALSE:
                    setBooleanValues(employee, address, keyName, false);
                    break;
                case VALUE_TRUE:
                    setBooleanValues(employee, address, keyName, true);
                    break;
                case VALUE_NULL:
                    // don't set anything
                    break;
                default:
                    // we are not looking for other events
            }
        }
        employee.setAddress(address);
        long[] nums = new long[phoneNums.size()];
        int index = 0;
        for(Long l : phoneNums){
            nums[index++] = 1;
        }
        employee.setPhoneNumbers(nums);
        System.out.println(employee);

        // close resources
        inputStream.close();
        jsonParser.close();
    }

    static void setNumberValues(Employee employee, Address address, String key, long value, List<Long> phoneNums){
        switch (key){
            case "zipcode":
                address.setZipcode((int)value);
                break;
            case "id":
                employee.setId((int)value);
                break;
            case "phoneNumbers":
                phoneNums.add(value);
                break;
            default:
                System.out.println("Unknown Key=" + key);
        }
    }

    static void setBooleanValues(Employee employee, Address address, String key, boolean value){
        switch (key){
            case "permanent":
                employee.setPermanent(value);
                break;
            default:
                System.out.println("Unknown Key=" + key);
        }
    }

    static void setStringValues(Employee employee, Address address, String key, String value){

        switch (key){
            case "name":
                employee.setName(value);
                break;
            case "role":
                employee.setRole(value);
                break;
            case "city":
                address.setCity(value);
                break;
            case "street":
                address.setStreet(value);
                break;
            default:
                System.out.println("Unknown Key=" + key);
        }
    }
}
