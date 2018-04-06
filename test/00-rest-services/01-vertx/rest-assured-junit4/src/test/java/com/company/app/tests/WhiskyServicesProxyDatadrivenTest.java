package com.company.app.tests;

import com.company.app.WhiskyClientImpl;
import com.company.app.WhiskyScenarioProxy;
import com.company.app.WhiskyValidator;
import com.company.app.WhiskyValidatorBase;
import com.jayway.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class WhiskyServicesProxyDatadrivenTest {

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        {"create"}, {"update"}
    });
  }

  @Parameterized.Parameter
  public String methodName;

  private static WhiskyScenarioProxy whisky;

  @BeforeClass
  public static void configureRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);
    WhiskyValidatorBase whiskyValidator = new WhiskyValidator(new WhiskyClientImpl());
    whisky = new WhiskyScenarioProxy(whiskyValidator);
  }

  @AfterClass
  public static void unconfigureRestAssured() {
    RestAssured.reset();
  }

  @Before
  public void setUp() {
  }

  // curl -X PUT http://localhost:8080/api/whiskies/{id} -d '{"name": "Bowmore 18", "origin": "Scotland"}'
  @Test
  public void scenariosTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    System.out.println(methodName);
    whisky.getClass().getDeclaredMethod(methodName).invoke(whisky, null);
  }


}
