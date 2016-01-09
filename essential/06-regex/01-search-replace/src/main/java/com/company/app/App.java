package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        String env = "test";
        String queryString = "/?ci=13931&iphoneview=1";
        String url = "https://www.godaddy.com";
        url = url.replaceFirst("www.", "www." + env + "-");
        url = url.replaceFirst(".com", ".com" + queryString);
        System.out.println(url);
    }
}
/*
output:
https://www.test-godaddy.com/?ci=13931&iphoneview=1
 */
