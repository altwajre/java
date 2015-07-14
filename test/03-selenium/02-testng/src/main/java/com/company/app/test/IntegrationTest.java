package com.company.app.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IntegrationTest {
    @Test(priority = 1)
    public void verifyTitle_test(){
        String relativePath = "src/selenium-driver/chromedriver";
        System.setProperty("webdriver.chrome.driver", relativePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("#" + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
    }
}
