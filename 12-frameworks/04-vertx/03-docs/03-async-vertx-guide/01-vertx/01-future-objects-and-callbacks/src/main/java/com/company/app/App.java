package com.company.app;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Customer {
    private String name;
    private Integer age;
}

interface Worker {
    @Fluent
    void doWork(Handler<AsyncResult<Customer>> callback);
}

@Data
@AllArgsConstructor
class Salesman implements Worker {
    private Customer customer;

    @Override
    public void doWork(Handler<AsyncResult<Customer>> callback) {
        Future<Customer> future = Future.future();

        // customer is AsyncResult
        future.complete(customer);
        callback.handle(future);
    }
}

/*
A word on Vert.x future objects and callbacks

 */
public class App
{
    public static void main( String[] args )
    {
        Salesman salesman = new Salesman(new Customer("Tom", 28));
        salesman.doWork(ar -> {
            System.out.println(ar);
            System.out.println(ar.result());
            System.out.println("hello from doWork() lambda");
        });

    }
}
/*
output:
Future{result=Customer(name=Tom, age=28)}
Customer(name=Tom, age=28)
hello from doWork() lambda
 */
