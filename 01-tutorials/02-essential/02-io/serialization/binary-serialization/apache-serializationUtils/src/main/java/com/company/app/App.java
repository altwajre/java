package com.company.app;

import org.apache.commons.lang3.SerializationUtils;

public class App
{
    public static void main( String[] args )
    {
        Address address = new Address();
        address.setStreet("wall street");
        address.setCountry("united state");

        byte[] data = SerializationUtils.serialize(address);

        Address serializedAddress = (Address)SerializationUtils.deserialize(data);
        System.out.println(serializedAddress.getStreet());
        System.out.println(serializedAddress.getCountry());
    }
}
