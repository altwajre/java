package com.company.app.test;

import com.company.app.infrastructure.DriverInfo;
import com.company.app.test.base.IntegrationTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class CreateDriverFromConfigTest extends IntegrationTestBase {
    /*
    dataProvider = "desktopPlatforms" - get an array of test data Object[][WebDriverBuilder]
    from @DataProvider name="desktopPlatforms" method in base class
    @Factory - use dataProvider Object[][WebDriverBuilder] to create instances of test classes dynamically
     */
//    @Factory(dataProvider = "local") // debug in local
    @Factory(dataProvider = "createDesktopTestData")
    public CreateDriverFromConfigTest(DriverInfo driverInfo) {
        super(driverInfo);
    }

    @Test(priority = 1)
    public void verifyTitle_test(){
        System.out.println("#Test: " + driverInfo.getPlatType());

        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("#" + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
    }
}
