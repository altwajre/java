package com.company.app;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/*
decorated the main method with the throws clause as below will report a compiler error.
​public​ ​static​ ​void​ main(​String​​[]​ args) ​throws​ IOException {...}
solutions:
1, handle exception within lambda expression
2, catch the checked exception and rethrow an unchecked exception
 */
public class App
{

    public static void main( String[] args )
    {
        handleException();
        rethrowUncheckedException();
    }

    // catch the checked exception and rethrow an unchecked exception
    private static void rethrowUncheckedException() {
        Stream.of("/usr", "/tmp")
                .map(path -> {
                    try {
                        return new File(path).getCanonicalPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                })
                .forEach(System.out::println);
    }

    /*
    handle the exception right within the lambda expression
    few caveats in concurrent execution:
    1, an exception raised within the lambda expressions will be propagated automatically to the calling primary thread.
       two issues:
       a, this will not terminate or obstruct the execution of other lambda expressions running concurrently.
       b, if exceptions are thrown from multiple concurrent executions, only one of them will be reported in the catch block.
          If the details of all the exceptions are important, it's better to capture those within the lambda expressions and
          pass them back to the main thread as part of the result.
     */
    private static void handleException() {
        Stream.of("/usr", "/tmp")
                .map(path -> {
                    try {
                        return new File(path).getCanonicalPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return e.getMessage();
                    }
                })
                .forEach(System.out::println);
    }
}
/*
output:
/usr
/private/tmp
 */
