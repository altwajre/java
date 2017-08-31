package com.company.app.ArgumentMatchers;

import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/*
https://static.javadoc.io/org.mockito/mockito-core/2.8.47/org/mockito/ArgumentMatchers.html
 */
public class ArgumentMatchersTest {
  class MyClass {
    public String myMethod(String name, int age){
      return name + ", " + age;
    }
  }

  @Test
  public void testArgumentMatchers() {

    // given
    MyClass myClass = mock(MyClass.class);
    given(myClass.myMethod(contains("Tom"), anyInt()))
        .willReturn("Tom, 28");

    // when
    final String result = myClass.myMethod("Tom", 1);
    System.out.println(result);

    // then
    then(result).isEqualTo("Tom, 28");
  }
}
