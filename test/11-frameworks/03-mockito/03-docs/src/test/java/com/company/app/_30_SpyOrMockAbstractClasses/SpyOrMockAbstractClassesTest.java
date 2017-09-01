package com.company.app._30_SpyOrMockAbstractClasses;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.withSettings;

public class SpyOrMockAbstractClassesTest {
  static abstract class AbstractMessage {
    private final String message;
    AbstractMessage() {
      this.message = "hey!";
    }
    AbstractMessage(String message) {
      this.message = message;
    }
    AbstractMessage(int i) {
      this.message = String.valueOf(i);
    }
    String getMessage() {
      return message;
    }
  }

  @Test
  public void canMockAbstractClasses() {
    AbstractMessage mock = mock(AbstractMessage.class, withSettings().useConstructor().defaultAnswer(CALLS_REAL_METHODS));
    assertEquals("hey!", mock.getMessage());
  }

  @Test
  public void canSpyAbstractClasses() {
    AbstractMessage mock = spy(AbstractMessage.class);
    assertEquals("hey!", mock.getMessage());
  }
}
