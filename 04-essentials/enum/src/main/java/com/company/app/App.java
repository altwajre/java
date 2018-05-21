package com.company.app;

public class App {
  public enum FlowDirection {
    RightToLeft, LeftToRight
  }

  public static void main(String[] args) {
    System.out.println(FlowDirection.LeftToRight);

    System.out.println("# test()");
    test(FlowDirection.RightToLeft);
  }

  public static void test(FlowDirection flowDirection) {
    switch (flowDirection) {
      case LeftToRight:
        System.out.println("LeftToRight");
        break;
      case RightToLeft:
        System.out.println("RightToLeft");
        break;
      default:
        throw new RuntimeException("unknown");
    }
  }
}
/*
LeftToRight
# test()
RightToLeft
 */
