package com. company.app;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws IOException {

        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("curl/NewShopper.curl");
        String result = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(result);
        result = result.substring(result.indexOf(" ")+1);

//        String[] parts = result.split("-H ");
//        System.out.println(parts);
//        String part = parts[0];
//        part = part.substring(1, part.indexOf("\" "));
//        System.out.println(part);


        String httpVerb = getHttpVerb(result);
        System.out.println(httpVerb);

    }

    private static String getHttpVerb(String result) {
        String[] parts = result.split("-X ");
        System.out.println(parts);
        String part = parts[0];
        return part.substring(0, part.indexOf(" "));
    }
}
