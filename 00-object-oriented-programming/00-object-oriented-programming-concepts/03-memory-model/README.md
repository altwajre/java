# The Java Memory Model

https://www.youtube.com/watch?v=LTnp79Ke8FI

- All data for primitive type variable is stored on the stack
- For reference types, the stack holds a pointer to the object on the heap

## Everything Is a Reference

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video3_1

- When passing a primitive variable to a method we create a copy of the value of that variable and pass that copy
- When passing a reference variable to a method we create a copy of the value of that reference and pass that copy

## The Heap and the Stack

- Instance variables are declared in the Class (outside methods, constructors & initialization blocks)
- Local variables are declared in methods or constructors or initialization blocks
- Both instance and local variables can be either primitive or reference variables

> instance variables (and objects) live in the heap

- If not explicitly initialized, a reference instance variable is given the null value
- If not explicitly initialized, a primitive instance variable is given a value according to its type (int to '0', char to '\u000', float to '0.0')

> local variables live in the stack

- Reference local variables are not given a default value if not initialized, the developer must provide a value (null or point to an object)
- Primitive local variables are not given a default value if not initialized, the developer must provide a value

```
public class ClassA {
  public String instVar1;
  public String instVar2;

  public static void main(String[] args) {
    ClassA localRef1 = null;
    localRef1 = new ClassA();
    localRef1.setInstVars(localRef1);
  }

  void setInstVars(ClassA localRef2) {
    localRef2.instVar1 = "aaa";
    localRef2.setInstVar2("bbb");
  }

  void setInstVar2(String localRef3) {
    this.instVar2 = localRef3;
    String localRef4 = "ccc";
    this.instVar2 = localRef4;
  }
}
```

## Garbage Collection

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video3_3

http://www.tothenew.com/blog/java-garbage-collection-an-overview/
