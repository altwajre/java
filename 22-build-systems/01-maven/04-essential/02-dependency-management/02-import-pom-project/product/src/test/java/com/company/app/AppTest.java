package com.company.app;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AppTest {
  @Test
  public void testProduct() {
    System.out.println("# testProduct");
    List<String> names = Arrays.asList("product_1", "product_2", "product_3");
    names.forEach(System.out::println);
  }
}

