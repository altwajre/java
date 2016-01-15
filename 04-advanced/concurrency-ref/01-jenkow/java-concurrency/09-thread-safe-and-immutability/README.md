## Thread safety and Immutability

### Thread safe

#### Immutability

```
ImmutableObject is thread safe:
Race conditions occur only if multiple threads are accessing the same resource, and one or more of the threads WRITE
to the resource. If multiple threads read the same resource race conditions do not occur.
We can make sure that objects shared between threads are never updated by any of the threads by making the shared
objects immutable, and thereby thread safe.
Notice how the value of the ImmutableObject instance is passed in the constructor. Notice also how there is no setter
method. Once an ImmutableObject instance is created you cannot change its value. It is immutable. You can read it by
using the getValue() method.
class ImmutableObject{ // thread safe
    private final int value;
    public ImmutableObject(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public ImmutableObject add(int value){
        return new ImmutableObject(this.value + value);
    }
}
If you need to perform operations like add, you can do so by returning a new instance
with the value resulting from operation.
Notice how the add() method returns a new ImmutableValue instance with the result of the add operation, rather than
adding the value to itself.
```

#### synchronized reference to immutable object

```
To make the Calculator class thread safe you could have declared the getValue(), setValue(), and add() methods
synchronized.
class Calculator {
    private ImmutableObject value = null;
    public ImmutableObject getValue(){ return value; }
    public synchronized void setValue(ImmutableObject value){
        this.value = value;
    }
    public synchronized void add(int value){
        this.value = this.value.add(value);
    }
}
```

### Not thread safe

#### Reference to immutable object

```
The reference to an immutable object may not be thread safe.
The Calculator class holds a reference to an ImmutableObject instance. Notice how it is possible to change that
reference through both the setValue() and add() methods. Therefore, even if the Calculator class uses an immutable
object internally, it is not itself immutable, and therefore not thread safe.
class Calculator {
    private ImmutableObject value = null;
    public ImmutableObject getValue(){ return value; }
    public void setValue(ImmutableObject value){
        this.value = value;
    }
    public void add(int value){
        this.value = this.value.add(value);
    }
}
```
