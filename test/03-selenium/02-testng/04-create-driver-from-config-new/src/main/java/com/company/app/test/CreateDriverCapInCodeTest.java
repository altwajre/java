package com.company.app.test;

import com.company.app.infrastructure.Global;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CreateDriverCapInCodeTest {
    private WebDriver driver;
    @BeforeClass(alwaysRun = true)
    public void openClient() throws MalformedURLException {
        URL url = new URL("http://" + Global.Config.getUsername() + ":" + Global.Config.getKey() + "@ondemand.saucelabs.com:80/wd/hub");
        System.out.println("#" + Global.Config.getUsername());
        System.out.println("#CapabilityType.BROWSER_NAME: " + CapabilityType.BROWSER_NAME);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserName", "safari");
        cap.setCapability("version", "6");
        cap.setCapability("platform", "OSX 10.8");
        cap.setCapability("name", "CreateCapInCodeTest - safari OSX");
        driver = new RemoteWebDriver(
                url,
                cap);
    }
    @AfterClass(alwaysRun = true)
    public void closeClient(){ this.driver.quit(); }
    @Test(priority = 1)
    public void verifyTitle_test(){
        driver.get("http://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("#" + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
    }
}
