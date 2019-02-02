package gof.creational.singleton;

/*
Enforce the singleton property with a private constructor or an enum type

https://www.safaribooksonline.com/library/view/effective-java-2nd/9780137150021/ch02.html#ch02lev1sec3

Enum singleton - the preferred approach
1, it is equivalent to the public field approach
2, it provides the serialization machinery for free
3, it provides a guarantee against multiple instantiation, even facing serialization and reflection attacks.
 */
enum Elvis {
  INSTANCE;

  public void leaveTheBuilding() {
    System.out.println("Whoa baby, I'm outta here!");
  }
}

public class Effective {
  public static void main(String[] args) {
    final Elvis elvis = Elvis.INSTANCE;
    elvis.leaveTheBuilding();
  }
}
/*
Whoa baby, I'm outta here!
 */

