package com.company;

public class App
{
    static abstract class Test{
        public void run(){}
    }

    static class ButtonTest extends Test{
        public void run() {
            System.out.println("Button Test");
        }
    }

    static class TextBoxTest extends Test{
        public void run() {
            System.out.println("TextBox Test");
        }
    }

    public static void main( String[] args )
    {
        Test tests[] = new Test[2];
        tests[0] = new ButtonTest();
        tests[1] = new TextBoxTest();
        for(int i = 0; i < tests.length; i++){
            tests[i].run();
        }
    }
}
