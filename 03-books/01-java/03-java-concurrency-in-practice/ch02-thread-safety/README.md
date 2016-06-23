# Thread Safety

```
A class is thread-safe if it behaves correctly when accessed from multiple threads, regardless of the scheduling or
interleaving of the execution of those threads by the runtime environment, and with no additonal synchronization or
other coordination on the part of the calling code.
```

static methods
> http://stackoverflow.com/questions/13910976/how-to-ensure-thread-safety-of-utility-static-method

```
static methods with immutable objects as parameters are thread safe, but mutable objects are not.
```
