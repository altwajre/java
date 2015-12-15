package com.company.app;

import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        Map<String, String> env = System.getenv();
        for(String envName : env.keySet()){
            System.out.format("%s=%s%n", envName, env.get(envName));
        }

        // gets the value of the specified environment variable "PATH"
        System.out.println("System.getenv(\"PATH\") = ");
        System.out.println(System.getenv("PATH"));

        // $ set TEST_ENV in terminal `export TEST_ENV=dev`
        System.out.println(System.getenv("TEST_ENV"));
    }
}
