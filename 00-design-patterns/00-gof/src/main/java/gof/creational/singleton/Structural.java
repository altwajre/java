package gof.creational.singleton;

class Singleton{
  private static Singleton instance;
  protected Singleton(){

  }
  public static Singleton Instance(){
    // Uses lazy initialization.
    // Note: this is not thread safe.
    if(instance == null){
      instance = new Singleton();
    }
    return instance;
  }
}

public class Structural {
  public static void main( String[] args )
  {
    Singleton s1 = Singleton.Instance();
    Singleton s2 = Singleton.Instance();
    if(s1 == s2){
      System.out.printf("Object are the same instance");
    }
    else{
      System.out.printf("Object are NOT the same instance");
    }
  }
}
/*
Definition
Ensure a class has only one instance and provide a global point of access to it.

output:
Object are the same instance
 */
