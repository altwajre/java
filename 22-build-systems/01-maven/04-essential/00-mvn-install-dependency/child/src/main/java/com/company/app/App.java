package com.company.app;

import com.company.app.model.Customer;

public class App
{
    public static void main( String[] args )
    {
        Customer customer = new Customer();
        customer.setName("Tom");
        System.out.println(customer.getName());
    }
}
