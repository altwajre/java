package com.company.app;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ScoreCollectionTest {
  @Test
  public void answersArithmeticMeanOfTwoNumbers() {
    // Arrange
    ScoreCollection collection = new ScoreCollection();
    collection.add(() -> 5);
    collection.add(() -> 7);

    // Act
    int actualResult = collection.arithmeticMean();

    // Assert
    assertThat(actualResult, equalTo(6));
  }

  @Test
  public void testFunctionalInterfaceLambda() {
    final Scoreable scoreable = () -> 8;
    System.out.println(scoreable.getScore());
  }
/*
8
 */

  @Test
  public void testFunctionalInterfaceAnonymousClass() {
    final Scoreable scoreable = new Scoreable() {
      @Override
      public int getScore() {
        return 8;
      }
    };
    System.out.println(scoreable.getScore());
  }
/*
8
 */

}