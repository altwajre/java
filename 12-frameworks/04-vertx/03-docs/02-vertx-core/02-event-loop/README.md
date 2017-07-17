# Reactor and Multi-Reactor

http://vertx.io/docs/vertx-core/java/#_reactor_and_multi_reactor

## Don't block thread!

With very few exceptions (i.e. some file system operations ending in 'Sync'), none of the APIs in Vert.x block the calling thread.

If a result can be provided immediately, it will be returned immediately, otherwise you will usually provide a handler to receive events some time later.

Because none of the Vert.x APIs block threads that means you can use Vert.x to handle a lot of concurrency using just a small number of threads.

## The Golden Rule - Don’t Block the Event Loop

We already know that the Vert.x APIs are non blocking and won’t block the event loop, but that’s not much help if you block the event loop yourself in a handler.

If you do that, then that event loop will not be able to do anything else while it’s blocked. If you block all of the event loops in Vertx instance then your application will grind to a complete halt!

So don’t do it! You have been warned.

Examples of blocking include:

- Thread.sleep()
- Waiting on a lock
- Waiting on a mutex or monitor (e.g. synchronized section)
- Doing a long lived database operation and waiting for a result
- Doing a complex calculation that takes some significant time.
- Spinning in a loop

If any of the above stop the event loop from doing anything else for a significant amount of time then you should go immediately to the naughty step, and await further instructions.

So…​ what is a significant amount of time?

How long is a piece of string? It really depends on your application and the amount of concurrency you require.

If you have a single event loop, and you want to handle 10000 http requests per second, then it’s clear that each request can’t take more than 0.1 ms to process, so you can’t block for any more time than that.

The maths is not hard and shall be left as an exercise for the reader.

If your application is not responsive it might be a sign that you are blocking an event loop somewhere. To help you diagnose such issues, Vert.x will automatically log warnings if it detects an event loop hasn’t returned for some time. If you see warnings like these in your logs, then you should investigate.

Thread vertx-eventloop-thread-3 has been blocked for 20458 ms
