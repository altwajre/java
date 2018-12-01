/*
Identity Versus Equality

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video4_1?autoplay=false
 */
class ClassA {
  private int myInt;
  private String myString;

  public ClassA(int i) {
    this.myInt = i;
  }

  public String getMyString() {
    return myString;
  }

  public void setMyString(String myString) {
    this.myString = myString;
  }

  public int getMyInt() {
    return myInt;
  }

  public void setMyInt(int i) {
    this.myInt = i;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof ClassA) {
      if (((ClassA) obj).getMyInt() == this.getMyInt()) {
        return true;
      }
    }
    return false;
  }
}

public class IdentityVsEquality {
  public static void main(String[] args) {
    ClassA a1 = new ClassA(5);
    ClassA a2 = new ClassA(5);

    ClassA a3 = a2;

    System.out.println("# reference equal - are references point to the same object");
    System.out.println("a1 == a2: " + (a1 == a2));
    System.out.println("a3 == a2: " + (a3 == a2));

    System.out.println("# meaningful equal - override equals()");
    System.out.println("a1.equals(a2): " + a1.equals(a2));
    System.out.println("a3.equals(a2): " + a3.equals(a2));
  }
}
/*
# reference equal - are references point to the same object
a1 == a2: false
a3 == a2: true
# meaningful equal - override equals()
a1.equals(a2): true
a3.equals(a2): true
 */
