package com.company.app._03_ArgumentMatchers;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.LinkedList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
3. Argument matchers
- Argument matchers allow flexible verification or stubbing.
- If you are using argument matchers, all arguments have to be provided by matchers.
 */
public class ArgumentMatchersTest {
  @Test
  public void argumentMatchers() {
    LinkedList mockedList = mock(LinkedList.class);

    // stubbing using built-in anyInt() argument matcher
    when(mockedList.get(anyInt())).thenReturn("element");

    // following prints "element"
    System.out.println(mockedList.get(999));

    // you can also verify using an argument matcher
    verify(mockedList).get(anyInt());

    // stubbing using custom matcher (let's say isValid() returns your own matcher implementation)
    when(mockedList.contains(argThat(isEvenNumber()))).thenReturn(true);

    System.out.println(mockedList.contains(8)); // true because 8 is even number
    System.out.println(mockedList.contains(11)); // false because 11 is not even number

    // verify custom argument matcher
    verify(mockedList).contains(argThat(isEvenNumber()));
  }

  private ArgumentMatcher<Integer> isEvenNumber() {
    return new ArgumentMatcher<Integer>() {
      @Override
      public boolean matches(Integer integer) {
        if (integer % 2 == 0) {
          return true;
        } else {
          return false;
        }
      }
    };
  }

  // lambda version
//  private ArgumentMatcher<Integer> isEvenNumber() {
//    return obj -> {
//      int input = (int) obj;
//      if (input % 2 == 0) {
//        return true;
//      } else {
//        return false;
//      }
//    };
//  }
}
