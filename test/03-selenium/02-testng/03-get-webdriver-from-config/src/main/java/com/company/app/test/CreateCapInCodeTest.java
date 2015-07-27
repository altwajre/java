//package com.company.app.test;
//
//import com.company.app.infrastructure.Global;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CreateCapInCodeTest {
//    private WebDriver driver;
//    @BeforeClass(alwaysRun = true)
//    public void openClient() throws MalformedURLException {
//
////        this.driver = driverBuilder.createDriver(); // <-- read info from yml to RemoteWebDriver DesiredCapabilities
//        URL url = new URL("http://" + Global.Config.getUsername() + ":" + Global.Config.getKey() + "@ondemand.saucelabs.com:80/wd/hub");
//
//        System.out.println("#" + Global.Config.getUsername());
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability("browserName", "safari");
//        cap.setCapability("version", "6");
//        cap.setCapability("platform", "OSX 10.8");
//        cap.setCapability("name", "safari OSX");
//
//        cap.setCapability("browserName", "chrome");
//        cap.setCapability("version", "43.0");
//        cap.setCapability("platform", "Windows 8.1");
//        cap.setCapability("name", "chrome windows");
//
////        URL url = new URL("http://localhost:4444/wd/hub");
////        DesiredCapabilities cap = DesiredCapabilities.firefox();
//
//
//        driver = new RemoteWebDriver(
//                url,
//                cap);
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void closeClient(){
//        this.driver.quit();
//    }
//
//    @Test(priority = 1)
//    public void verifyTitle_test(){
//        driver.get("http://www.google.com");
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Cheese!");
//        element.submit();
//        System.out.println("#" + driver.getTitle());
//        Assert.assertEquals(driver.getTitle(), "Google");
//        driver.quit();  // uncomment out it to close browser
//    }
//
//}
