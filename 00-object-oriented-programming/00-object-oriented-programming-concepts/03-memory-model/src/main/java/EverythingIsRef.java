/*
https://www.youtube.com/watch?v=LTnp79Ke8FI

- All data for primitive type variable is stored on the stack
- For reference types, the stack holds a pointer to the object on the heap

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video3_1

Stack - methods and local-variables
Heap - objects (instance-variables)
 */

// When passing a primitive variable to a method we create a copy of the value of that variable and pass that copy
class PassByValue {
  public static void main(String[] args) {
    int localvar1 = 5;
    PassByValue ref1 = new PassByValue();
    ref1.someMethod(localvar1);
    System.out.println("main - primitive int localvar1: " + localvar1); // still 5
  }

  public void someMethod(int localVar2) {
    localVar2 = 7;  // change the value
    System.out.println("someMethod - copy of primitive int localVar2: " + localVar2);
  }
}
/*
someMethod - copy of primitive int localVar2: 7
main - primitive int localvar1: 5
 */

// When passing a reference variable to a method we create a copy of the value of that reference and pass that copy
class PassByReference {
  public int instVar = 5;

  public static void main(String[] args) {
    PassByReference localVar1 = new PassByReference();
    localVar1.someMethod(localVar1);
    System.out.println("main - reference localVar1: " + localVar1.instVar); // 7
  }

  public void someMethod(PassByReference localVar2) {
    localVar2.instVar = 7;
    System.out.println("someMethod - reference localVar2: " + localVar2.instVar); // 7
  }
}
/*
someMethod - reference localVar2: 7
main - reference localVar1: 7
 */

public class EverythingIsRef {

}