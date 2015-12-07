package com.company.test.testcase;

import com.company.test.common.DriverFactory;
import com.company.test.pageobject.GodaddyHomePage;
import com.company.test.testcase.base.IntegrationTestBase;
import org.testng.annotations.*;

@Test
public class MobileGodaddyHomePageTest extends IntegrationTestBase {
    @Factory(dataProvider = "mobilePlatforms")
    public MobileGodaddyHomePageTest(DriverFactory driverFactory){
        super(driverFactory);
    }

    @Test(groups = "xdebug")
    public void testModules(){
        GodaddyHomePage godaddyHomePage = new GodaddyHomePage(driver, driverFactory.getPlatformType());
        godaddyHomePage.testModules();
    }
}
