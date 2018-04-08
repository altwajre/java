package com.company.app;

import java.util.UUID;

public class App
{
    public static void main( String[] args )
    {
        for(int i = 0; i < 3; i++){
            UUID uuid1 = UUID.randomUUID();
            System.out.println(uuid1);
        }
    }
}
