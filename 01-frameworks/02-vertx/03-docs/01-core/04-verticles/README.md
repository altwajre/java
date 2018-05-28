# Verticles

The different verticle instances communicate with each other by sending messages on the event bus.

## Verticle Types

> Standard Verticles

These are the most common and useful type - they are always executed using an event loop thread.

> Worker Verticles

These run using a thread from the worker pool. An instance is never executed concurrently by more than one thread.

> Multi-threaded worker verticles

These run using a thread from the worker pool. An instance can be executed concurrently by more than one thread.
