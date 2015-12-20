package com.company.test.moduleobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DesktopHomepageMarqueeModule implements SitecoreModule {
    WebDriver driver;
    WebElement icon;

    public DesktopHomepageMarqueeModule(WebDriver driver){
        this.driver = driver;
        icon = driver.findElement(By.className("hero-icon"));
    }
    @Override
    public void run() {
        System.out.println("### DesktopHomepageMarqueeModule.run()");
        String temp = icon.getAttribute("class");
        System.out.println("**"+temp);
    }
}
