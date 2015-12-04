package com.company.app.testbase;

import java.util.HashMap;
import java.util.Map;

public class Helper {
    Map<String, String> map = new HashMap<String, String>();
    static {
        System.out.println("static");
    }
    public static String getTestName(String key){
        System.out.println("static getTestName()");
        return "";
    }
}
