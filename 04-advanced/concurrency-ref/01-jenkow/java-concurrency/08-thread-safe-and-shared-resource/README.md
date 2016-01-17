# Thread safety and Shared Resources

## Thread safe

### Local Primitive Variables

```
Local variables are stored in each thread's own stack. That means that local variables are never shared between threads. 
That also means that all local primitive variables are thread safe.
    static int localPrimitiveVariable(){
        int count = 0; // local primitive variable is thread safe
        count++;
        return count;
    }
```

### Local Object References

```
Local references to objects are a bit different. The reference itself is not shared. The object referenced is not stored
in each threads's local stack. All objects are stored in the shared heap.
If an object created locally never escape the method it was created in, it is thread safe.
    static void localObjectReference(){
        LocalObject localObject = new LocalObject("Tom"); // local reference object is thread safe
        otherMethod(localObject);
    }
    static void otherMethod(LocalObject localObject){
        localObject.setName("Other name");
    }
```

### The Thread Control Escape Rule

```
If a resource is created, used and disposed within the control of the same thread, and never escapes the control of this
thread, the use of that resource is thread safe.
```
