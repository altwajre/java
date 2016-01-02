# Obey the general contract when overriding equals

## Logical equality vs object identity

### Logical equality

```
It is generally the case for value classes. A value class is simply a class that represents a value, such as Integer or
Date. A programmer who compares references to value objects using the equals method expects to find out whether they are
logically equivalent, not whether they refer to the same object. Not only is overriding the equals method necessary to
satisfy programmer expectations; it enables instances to serve as map keys or set elements with predictable, desirable
behavior.
```

### Object identity

```
Find out whether they refer to the same object.
```

### Override the equals method contract

```
The equals method implements an equivalence relation. It is:
  - Reflexive: For any non-null reference value x, x.equals(x) must return true.
  - Symmetric: For any non-null reference values x and y, x.equals(y) must return true if and only if y.equals(x) 
    returns true.
  - Transitive: For any non-null reference values x, y, z, if x.equals(y) returns true and y.equals(z) returns true, 
    then x.equals(z) must return true.
  - Consistent: For any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true 
    or consistently return false, provided no information used in equals comparisons on the objects is modified.
  - For any non-null reference value x, x.equals(null) must return false.
```

