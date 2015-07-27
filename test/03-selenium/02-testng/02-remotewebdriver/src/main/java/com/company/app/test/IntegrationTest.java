package com.company.app.test;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class IntegrationTest {
    @Test(priority = 1)
    public void verifyTitle_test() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("#" + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
//        driver.quit();  // uncomment out it to close browser
    }
}
