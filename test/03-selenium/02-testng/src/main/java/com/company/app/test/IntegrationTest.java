package com.company.app.test;

import com.company.app.infrastructure.Global;
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
        driver.get(Global.Config.getUrl());  // get url from yml config such as config.prod.yml
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("#" + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
    }
}
