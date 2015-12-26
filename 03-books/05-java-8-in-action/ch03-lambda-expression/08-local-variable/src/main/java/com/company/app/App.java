package com.company.app;

/*
Compile error when local variable is assigned twice
local variable have to be explicitly declared final or are effectively final. In other words, lambda
expressions can capture local variables that are assigned to them only once. For example, the following code doesn't
compile because the variable portNumber is assigned to twice.
 */
public class App
{
    public static void main( String[] args )
    {
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        portNumber = 123;
    }
}
/*
output:
Error:(8, 47) java: local variables referenced from a lambda expression must be final or effectively final
 */
