package com.company.app;

// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
public class App
{
    interface HelloWorld{
        void greet();
        void greetSomeone(String someone);
    }
    public void sayHello(){
        class EnglishGreeting implements HelloWorld{
            String name = "world";
            public void greet() {
                greetSomeone("world");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }
        HelloWorld englishGreeting = new EnglishGreeting();

        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";
            public void greet() {
                greetSomeone("mundo");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };
        englishGreeting.greet();
        spanishGreeting.greet();
    }
    public static void main( String[] args )
    {
        App app = new App();
        app.sayHello();
    }
}
/*
output:
Hello world
Hola, mundo
 */
