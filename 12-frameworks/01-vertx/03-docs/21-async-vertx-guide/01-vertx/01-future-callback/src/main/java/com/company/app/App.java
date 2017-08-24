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
    Future<Customer> doWork(Handler<AsyncResult<Customer>> callback);
}

@Data
@AllArgsConstructor
class Salesman implements Worker {
    private Customer customer;

    @Override
    public Future<Customer> doWork(Handler<AsyncResult<Customer>> callback) {
        Future<Customer> future = Future.future();

        // customer is AsyncResult
        future.complete(customer);
        callback.handle(future);

        return future;
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
        Future<Customer> customerFuture = salesman.doWork(ar -> {

            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + ar);
            System.out.println(threadName + ": " + ar.result());

        });

        Customer customer = customerFuture.result();
        System.out.println(Thread.currentThread().getName() + ": Future.result()=" + customer);

    }
}
/*
output:
main: Future{result=Customer(name=Tom, age=28)}
main: Customer(name=Tom, age=28)
main: Future.result()=Customer(name=Tom, age=28)
 */
