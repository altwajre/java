package _02_ConceptsAndAlgorithms.Math;

import org.junit.Test;

/*
https://en.wikipedia.org/wiki/Factorial
4! = 4 x 3 x 2 x 1 = 24
  */
public class Factorial {

  @Test
  public void Test() {
    System.out.println("4!: " + factorial(4));
  }

  public int factorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }
}
/*
output:
4!: 24
 */
