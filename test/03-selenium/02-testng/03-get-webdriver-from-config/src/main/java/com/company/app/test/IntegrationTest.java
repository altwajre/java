package com.company.app.test;

import com.company.app.infrastructure.IntegrationTestBase;
import com.company.app.infrastructure.WebDriverBuilder;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class IntegrationTest extends IntegrationTestBase {
    @Factory(dataProvider = "desktopPlatforms")
    public IntegrationTest(WebDriverBuilder driverFactory) {
        super(driverFactory);
    }

    @Test(priority = 1)
    public void verifyTitle_test(){
        System.out.println("#Test: " + driverFactory.name);


//        driver.get("http://www.google.com");
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Cheese!");
//        element.submit();
//        System.out.println("#" + driver.getTitle());
//        Assert.assertEquals(driver.getTitle(), "Google");
//        driver.quit();  // uncomment out it to close browser
    }
}
