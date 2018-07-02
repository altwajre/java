# promises and futures

> Writing asynchronous code with CompletableFuture

http://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/

> Code example

```
class MyPromise {
    private Future<Void> task1() {
        Future<Void> future = Future.future();
        System.out.println("task1");
        future.complete();
        return future;
    }
    private Future<Void> task2() {
        Future<Void> future = Future.future();
        System.out.println("task2");
        future.complete();
        return future;
    }
    public void run(Future<Void> startFuture){
        // promises: task1().compose(v -> task2())
        Future<Void> steps = task1().compose(v -> task2());
        steps.setHandler(ar -> {
            if(ar.succeeded()) {
                System.out.println("pass");
                startFuture.complete();
            }
            else {
                System.out.println("fail");
                startFuture.fail(ar.cause());
            }
        });
    }
}
```
