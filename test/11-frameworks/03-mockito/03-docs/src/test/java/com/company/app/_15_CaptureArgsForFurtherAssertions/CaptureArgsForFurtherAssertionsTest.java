package com.company.app._15_CaptureArgsForFurtherAssertions;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/*
15. Capturing arguments for further assertions
Sometimes, it is helpful to assert on certain arguments after the actual verification.

Test void methods `void BulkEmailService.email()` and `EmailService.sendEmailTo() is also void`
We use ArgumentCaptor to capture person argument of `void EmailService.sendEmailTo(Person person)` for further assertions
 */
public class CaptureArgsForFurtherAssertionsTest {
  class Person {
    private final Integer age;

    public Person(Integer age) {
      this.age = age;
    }

    public int getAge() {
      return age;
    }
  }

  @FunctionalInterface
  interface EmailService {
    void sendEmailTo(Person person);
  }

  class BulkEmailService {
    private EmailService service;

    public BulkEmailService(EmailService service) {
      this.service = service;
    }

    public void email(Integer... personId) {
      for (Integer i : personId) {
        Person person = new Person(i);
        service.sendEmailTo(person);
      }
    }
  }

  EmailService mock = mock(EmailService.class);
  BulkEmailService bulkEmailService = new BulkEmailService(mock);

  @Test
  public void shouldAllowAssertionsOnCapturedArgument() {
    // given
    final ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);

    // when
    bulkEmailService.email(12);

    // then
    verify(mock).sendEmailTo(argument.capture());
    assertEquals(12, argument.getValue().getAge());
  }

  @Test
  public void shouldAllowAssertionsOnAllCapturedArguments() {
    // given
    final ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);

    // when
    bulkEmailService.email(11, 12);

    // then
    verify(mock, times(2)).sendEmailTo(argument.capture());
    assertEquals(11, argument.getAllValues().get(0).getAge());
    assertEquals(12, argument.getAllValues().get(1).getAge());
  }

  @Test
  public void shouldAllowAssertionsOnLastArgument() {
    // given
    final ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);

    // when
    bulkEmailService.email(11, 12, 13);

    // then
    verify(mock, times(3)).sendEmailTo(argument.capture());
    assertEquals(13, argument.getValue().getAge());
  }

  @Test
  public void shouldAllowAssertionsOnCapturedNull() {
    // given
    final ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);

    // when
    mock.sendEmailTo(null);

    // then
    verify(mock).sendEmailTo(argument.capture());
    assertEquals(null, argument.getValue());
  }
}
