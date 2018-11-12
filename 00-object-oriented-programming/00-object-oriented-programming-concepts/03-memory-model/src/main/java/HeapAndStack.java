/*
The Heap and the Stack

https://www.youtube.com/watch?v=LTnp79Ke8FI

- All data for primitive type variable is stored on the stack
- For reference types, the stack holds a pointer to the object on the heap

Stack - methods and local-variables
Heap - objects (instance-variables)

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
 */
public class HeapAndStack {
  public String instVar1;
  public String instVar2;

  public static void main(String[] args) {
    HeapAndStack localRef1 = null;
    localRef1 = new HeapAndStack();
    localRef1.setInstVars(localRef1);
  }

  void setInstVars(HeapAndStack localRef2) {
    localRef2.instVar1 = "aaa";
    localRef2.setInstVar2("bbb");
  }

  void setInstVar2(String localRef3) {
    this.instVar2 = localRef3;
    String localRef4 = "ccc";
    this.instVar2 = localRef4;
  }
}
