package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ExceptionsTest {

  /*
  SIMPLE SCHOOL: USING AN ANNOTATION
  The JUnit @Test annotation supports passing an argument that specifies the type of an expected exception
   */
  @Test(expected = InsufficientFundsException.class)
  public void throwsWhenWithdrawingTooMuchAnnotation() {
    Account account = new Account("Tom's account");
    account.withdraw(100);
  }

  /*
  OLD SCHOOL: TRY AND FAIL-OR-CATCH
  The old-school technique is useful if you need to verify the state of things after the exception get thrown.
  Perhaps you want to verify the exception message.
   */
  @Test
  public void throwsWhenWithdrawingTooMuchTryCatch() {
    Account account = new Account("Tom's account");
    try {
      account.withdraw(100);
    }
    catch (InsufficientFundsException exception){
      assertThat(exception.getMessage(), equalTo("balance only 0"));
    }
  }

  /*
  NEW SCHOOL: EXPECTEDEXCEPTION RULES
  Junit allows you to define custom rules.
  The ExpectedException rule lets you combine the best of the simple school and the old school
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  @Test
  public void throwsWhenWithdrawingTooMuchExceptionRule() {
    thrown.expect(InsufficientFundsException.class);
    thrown.expectMessage("balance only 0");

    Account account = new Account("Tom's account");
    account.withdraw(100);
  }

  /*
  EXCEPTIONS SCHMEXCEPTIONS
  In test, you don't need to use try/catch blocks to handle the exception.
  Just throw the checked exceptions, and JUnit will handle the exception and report an error instead of a failure.
   */
  @Test
  public void readsFromTestFile() throws IOException {
    String filename = "test.txt";
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    writer.write("test data");
    writer.close();
    // ...
  }
}
