package com.company.app;


import org.json.simple.JSONObject;

public class App
{
    public static void main( String[] args )
    {
        JSONObject request = new JSONObject();
        request.put("url", "http://www.godaddy.com");
        request.put("requestBody", "Request Body");

        JSONObject headers = new JSONObject();
        headers.put("SOAPAction", "???");
        headers.put("Content-Type", "application/json");

        request.put("headers", headers);

        System.out.println(request);
    }
}
/*
output:
{"headers":{"SOAPAction":"???","Content-Type":"application/json"},"requestBody":"Request Body","url":"http://www.godaddy.com"}
 */
