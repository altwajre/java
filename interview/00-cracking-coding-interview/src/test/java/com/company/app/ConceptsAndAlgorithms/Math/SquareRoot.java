package com.company.app.ConceptsAndAlgorithms.Math;

import org.junit.Test;

public class SquareRoot {

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

  @Test
  public void findSqrtTest() {
    System.out.println(findSqrt(16));  // 4
  }

  int findSqrt(int num) {
    if(num < 0) {
      throw new IllegalArgumentException("Negative Number is not allowed");
    }
    else if (num == 0){
      return 0;
    }

    for (int i = 1; i <= num / 2; i++){
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

}
