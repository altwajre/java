package com.company.test.moduleobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TabletHomepageMarqueeModule implements SitecoreModule {
    WebDriver driver;
    WebElement icon;

    public TabletHomepageMarqueeModule(WebDriver driver){
        this.driver = driver;
        icon = driver.findElement(By.className("hero-icon"));
    }
    @Override
    public void run() {
        System.out.println("### TabletHomepageMarqueeModule.run()");
        String temp = icon.getAttribute("class");
        System.out.println("**"+temp);
    }
}
