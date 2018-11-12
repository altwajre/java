# The Heap and the Stack

> Everything Is a Reference

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video3_1

Stack
===
public static void main(String[] args) {
  ClassA ref1 = new ClassA();
  ref1.myPrim = 5;
  ref1.someMethod(ref1);
  System.out.println(ref1.myPrim); // 7
}
===
public void someMethod(ClassA ref2) {
  ref2.myPrim = 7;
  System.out.println(ref2.myPrim); // 7
}
===
 |
 V
Heap
===
(Object: myPrim = 5)
===

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video3_2

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
