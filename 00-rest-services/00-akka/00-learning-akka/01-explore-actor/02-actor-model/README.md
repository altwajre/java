# Understanding the Actor Model

## Concurrent applications

- In the past, programs just got faster by using the next generation of CUPs
- Now CPUs are not getting faster, they are getting wider
- You need to feed multi-cores

## The problems with shared mutable state

- A large number of stateful objects whose state can be changed by multiple parts of your application
- Solve this problem by preventing multiple threads from mutating data simultaneously

## Actor Model
