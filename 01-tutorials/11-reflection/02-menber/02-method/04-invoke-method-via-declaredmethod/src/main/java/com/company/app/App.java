package com.company.app;

import java.lang.reflect.Method;

public class App
{
    static class AppTest{
        public void printIt(){
            System.out.println("printIt() with no param");
        }
        public void printItString(String temp){
            System.out.format("printIt() with param String: %s\n", temp);
        }
        public void printItInt(int temp){
            System.out.format("printIt() with param int: %s\n", temp);
        }
    }
    public static void main( String[] args )
    {
        // no parameter
        Class[] noparams = {};
        // String parameter
        Class[] paramString = new Class[1];
        paramString[0] = String.class;
        // int parameter
        Class[] paramInt = new Class[1];
        paramInt[0] = Integer.TYPE;

        AppTest appTest = new AppTest();
        Class c = appTest.getClass();
        try{
            Method noParamMethod = c.getDeclaredMethod("printIt", noparams);
            noParamMethod.invoke(appTest, null);
            Method stringParamMethod = c.getDeclaredMethod("printItString", paramString);
            stringParamMethod.invoke(appTest, new String("foo"));
            Method intParamMethod = c.getDeclaredMethod("printItInt", paramInt);
            intParamMethod.invoke(appTest, 88);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
