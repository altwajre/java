package com.company.app._10_IteratorStyleStubbing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

// shorter version of consecutive stubbing
public class IteractorStyleStubbingShorterTest {
  @Mock
  private MyClass mock;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIteractorStyleStubbingShorterBDD() {
    // given
    given(mock.someMethod("some arg"))
        .willReturn("one", "two", "three");

    // when
    final String one = mock.someMethod("some arg");
    final String two = mock.someMethod("some arg");
    final String three = mock.someMethod("some arg");

    // then
    then(one).isEqualTo("one");
    then(two).isEqualTo("two");
    then(three).isEqualTo("three");
  }

  @Test
  public void testIteractorStyleStubbingShorter() {
    // Arrange
    when(mock.someMethod("some arg"))
        .thenReturn("one", "two", "three");

    // Act
    final String one = mock.someMethod("some arg");
    final String two = mock.someMethod("some arg");
    final String three = mock.someMethod("some arg");

    // Assert
    assertThat(one, equalTo("one"));
    assertThat(two, equalTo("two"));
    assertThat(three, equalTo("three"));
  }
}
