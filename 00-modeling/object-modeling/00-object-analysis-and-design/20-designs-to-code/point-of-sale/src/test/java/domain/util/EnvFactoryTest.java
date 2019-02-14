package domain.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnvFactoryTest {

  @Test
  public void getEnv() {
    EnvFactory envFactory = EnvFactory.INSTANCE;
    String env = envFactory.getEnv();
    System.out.println(env);
  }

}