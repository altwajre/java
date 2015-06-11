package com.company.ecomm.tests.testbase;

import com.company.ecomm.tests.helpers.Helpers;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestBase {
    Logger logger = Logger.getLogger(TestBase.class);

    private static Helpers helpers = new Helpers();

    @BeforeMethod(alwaysRun = true)
    protected void initTestRun(Method method){
        logger.info("Initializing test run Globals...");
        Global.Initialize();
    }
}
