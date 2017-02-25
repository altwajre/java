package com.company.app.test;

import com.company.app.Global;
import com.godaddy.logging.Logger;
import org.testng.annotations.Test;

import static com.godaddy.logging.LoggerFactory.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

public final class SimpleTest {
  private static final Logger logger = getLogger(SimpleTest.class);

  @Test
  public void should_equal_test() {
    logger.info("should_equal_test begin at " + Global.Config.getEnv());
    String actual = "foo";
    String expected = "foo";
    assertThat(actual).isEqualTo(expected);
  }
}
