package com.company.test;

import java.io.*;

public class App
{
    public static void main( String[] args ) throws Exception {
        Serializing();

        Deserializing();
    }

    private static void Deserializing() throws IOException, ClassNotFoundException {
        Address address = null;
        FileInputStream fileIn = new FileInputStream("/tmp/address.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        address = (Address) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("Deserialized Address...");
        System.out.println("Street: " + address.getStreet());
        System.out.println("Country: " + address.getCountry());
    }

    private static void Serializing() throws IOException {
        Address address = new Address();
        address.setStreet("wall street");
        address.setCountry("united state");

        FileOutputStream fileOut = new FileOutputStream("/tmp/address.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fileOut);
        oos.writeObject(address);
        oos.close();
        fileOut.close();
        System.out.println("Serialized data is saved in /tmp/address.ser");
    }
}
