# Concurrency vs Parallel

Parallel execution is concurrent by definition
Concurrency is not necessarily parallelism

In practice, this means being multithreaded is concurrency, 
but parallelism only occurs if those threads are being scheduled and executed on different CPUs at the exact same time. 

Thus, generically we speak about concurrency and being concurrent, but parallelism is a specific form of concurrency.
