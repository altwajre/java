# ThreadLocal

```
Java Thread Local

Thread local is a scope of access, like a request scope or session scope. It’s a thread scope. 
You can set any object in Thread Local and this object will be global and local to the specific thread which is accessing this object. 
  - Values stored in Thread Local are global to the thread, meaning that they can be accessed from anywhere inside that thread. 
    If thread calls methods from several classes, then all the methods can see the Thread local variable set by other methods. 
    The value need to be passed explicitly. It’s like how you use global variables.
  - Values stored in the Thread Local are local to the thread, meaning that each thread will have it’s own Thread local variable. 
    One thread can not access/modify other thread’s Thread Local variables.

When to use Thread Local?

When you have a Servlet which calls some business methods. You have a requirement to generate a unique transaction id for each and 
every request this servlet process and you need to pass this transaction id to the business methods, for logging purpose. 

The thread safe solution would use ThreadLocal. You can generate a transaction id and set it in the ThreadLocal. 
On each servlet calls, each thread can access the transaction id from the ThreadLocal.
```

## Compile

> `mvn package`

## Run

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App`

output

```
Thread-0
Thread-1

```
