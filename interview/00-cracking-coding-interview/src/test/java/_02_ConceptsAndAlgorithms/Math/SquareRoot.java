package _02_ConceptsAndAlgorithms.Math;

import org.junit.Test;

public class SquareRoot {

  @Test
  public void findSqrtBySquareITest() {
    System.out.println(findSqrtBySquareI(16));  // 4
  }

  int findSqrtBySquareI(int num) {
    if(num < 0) {
      throw new IllegalArgumentException("Negative Number is not allowed");
    }
    else if (num == 0){
      return 0;
    }

    for (int i = 1; i <= num / 2; i++){
      // square i
      int sqr = i * i;
      if(sqr == num){
        return i;
      }

      if(sqr > num){
        return i - 1;
      }
    }
    return -1;
  }

  @Test
  public void sqrtTest() {
    System.out.println(sqrt(25));  // 5
  }

  int sqrt(int n) {
    int start = 0;
    int end = n;
    while (start < end) {
      int mid = (start + end) / 2;
      int multiply = mid * mid;
      if (multiply == n) return mid;
      else if (multiply > n) end = mid;
      else start = mid;
    }
    return -1; // not found
  }
}
