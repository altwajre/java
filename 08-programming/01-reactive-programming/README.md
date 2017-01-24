# reactive programming

**github**

https://github.com/meddle0x53/learning-rxjava

**Summary**

Reactive-functional programming is solving is concurrency and parallelism.

The criteria is generally important is whether the Observable event production (pub) is blocking or nonblocking, 
not whether it is synchronous or asynchronous. 
The "Hello World" example below is nonblocking because it never blocks a thread, thus it is correct use of an Observable.

```
Observable.create(pub -> {
    pub.onNext("Hello World from rxjava");
    pub.onCompleted();
}).subscribe(sub -> {
    System.out.println(sub);
});
```

**Java 8 streams vs rxJava**

http://stackoverflow.com/questions/30216979/difference-between-java-8-streams-and-rxjava-observables
