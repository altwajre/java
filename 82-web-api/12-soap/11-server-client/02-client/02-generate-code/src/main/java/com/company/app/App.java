package com.company.app;

import soap.Demo;
import soap.DemoService;

public class App
{
    public static void main( String[] args )
    {
        DemoService demoService = new DemoService();
        Demo demoPort = demoService.getDemoPort(); // get the interface
        String tom = demoPort.getText("Tom"); // call service method
        System.out.println(tom);
    }
}
/*
output:
Hello Tom from soap
 */
