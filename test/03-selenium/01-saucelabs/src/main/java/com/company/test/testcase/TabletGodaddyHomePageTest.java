package com.company.test.testcase;

import com.company.test.common.DriverFactory;
import com.company.test.pageobject.GodaddyHomePage;
import com.company.test.testcase.base.IntegrationTestBase;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test
public class TabletGodaddyHomePageTest extends IntegrationTestBase {
    @Factory(dataProvider = "tabletPlatforms")
    public TabletGodaddyHomePageTest(DriverFactory driverFactory){
        super(driverFactory);
    }

    @Test(groups = "debug")
    public void testModules(){
        GodaddyHomePage godaddyHomePage = new GodaddyHomePage(driver, driverFactory.getPlatformType());
        godaddyHomePage.testModules();
    }
}
