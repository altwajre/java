package com.company.app;

/*
https://www.journaldev.com/2752/java-8-interface-changes-static-method-default-method
Java interface static method is similar to default method except that we can’t override them in the implementation classes.
This feature helps us in avoiding undesired results in case of poor implementation in implementation classes.
 */
interface MyData{
  default void print(String str) {
    if(!isNull(str))
      System.out.println("MyData Print: " + str);
  }
  static boolean isNull(String str) {
    System.out.println("Interface Null Check");
    return str == null ? true : "".equals(str) ? true : false;
  }
}
class MyDataImpl implements MyData {
  public boolean isNull(String str) {
    System.out.println("Impl Null Check");

    return str == null ? true : false;
  }
}
public class StaticMethodApp {
  public static void main(String args[]) {
    MyDataImpl myData = new MyDataImpl();
    myData.print("");
    myData.isNull("abc");
  }

}
