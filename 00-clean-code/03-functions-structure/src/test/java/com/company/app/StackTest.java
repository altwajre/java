package com.company.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

  private Stack stack;

  @Before
  public void setUp() throws Exception {
    stack = BoundedStack.Make(2);
  }

  @Test
  public void newlyCreateStack_ShouldBeEmpty() throws Exception {
    assertTrue(stack.isEmpty());
    assertEquals(0, stack.getSize());
  }

  @Test
  public void afterOnePush_StackSizeShouldBeOne() throws Exception {
    stack.push(1);
    assertEquals(1, stack.getSize());
    assertFalse(stack.isEmpty());
  }

  @Test
  public void afterOnePushAndOnePop_ShouldBeEmpty() throws Exception {
    stack.push(1);
    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test(expected = BoundedStack.Overflow.class)
  public void whenPushedPastLimit_StackOverflows() throws Exception {
    stack.push(1);
    stack.push(1);
    stack.push(1);
  }

  @Test(expected = BoundedStack.Underflow.class)
  public void whenEmptyStackIsPopped_ShouldThrowUnderFlow() throws Exception {
    stack.pop();
  }

  @Test
  public void whenOneIsPushed_OneIsPopped() throws Exception {
    stack.push(1);
    assertEquals(1, stack.pop());
  }

  @Test
  public void whenOneAndTwoArePushed_TwoAndOneArePopped() throws Exception {
    stack.push(1);
    stack.push(2);
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
  }

  @Test(expected = Stack.IllegalCapacity.class)
  public void whenCreatingStackWithNegativeSize_ShouldThrowIllegalCapacity() throws Exception {
    BoundedStack.Make(-1);
  }

  @Test(expected = BoundedStack.Overflow.class)
  public void whenCreatingStackWithZeroCapacity_AnyPushShouldOverflow() throws Exception {
    stack = BoundedStack.Make(0);
    stack.push(1);
  }

  @Test
  public void whenOneIsPushed_OneIsOnTop() throws Exception {
    stack.push(1);
    assertEquals(1, stack.top());
  }

  @Test(expected = Stack.Empty.class)
  public void whenStackIsEmpty_TopThrowsEmpty() throws Exception {
    stack.top();
  }

  @Test(expected = Stack.Empty.class)
  public void withZeroCapacityStack_TopThrowsEmpty() throws Exception {
    stack = BoundedStack.Make(0);
    stack.top();
  }

  @Test
  public void givenStackWithOneTwoPushed_FineOneAndTwo() throws Exception {
    stack.push(1);
    stack.push(2);
    int oneIndex = stack.find(1);
    int twoIndex = stack.find(2);
    assertEquals(1, oneIndex);
    assertEquals(0, twoIndex);
  }

  @Test
  public void givenStackWithNo2_Find2ShouldReturnNull() throws Exception {
    assertNull(stack.find(2));
  }
}
