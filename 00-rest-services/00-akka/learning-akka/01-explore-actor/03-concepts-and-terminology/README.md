# Concepts and Terminology

## Concurrency vs Parallelism

- Concurrency is when two tasks may start, run and complete with overlapping time periods (Multitasking on single core machine)
- Parallelism is when tasks literally run at the same time (Multithreads on Multicore processor)

## Asynchronous vs Synchronous

- A method call is synchronous if the caller cannot make progress until the method returns a value or throws an exception
- A method call is asynchronous if the caller can make progress after a finite number of steps, and the completion if the method may be signaled via some additional mechanism (callback, future or message)

## Non-blocking vs Blocking

- Blocking is when if the delay of one thread can indefinitely delay some of the other threads
- On another hand, Non-blocking means that no thread is able to indefinitely delay others

## Race condition

- We call Race condition when multiples have a shared mutable stat. In Fact, problems only arise if one or more of the threads try to change this state at the same time.
