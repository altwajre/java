package com.company.app.testbase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DesktopDomainSearchBannerModuleTest implements ModuleTest {
    public void run(WebElement moduleElement) {
        System.out.println("DesktopDomainSearchBannerModuleTest.run()");
        String classAttrValue = moduleElement.getAttribute("class");
        System.out.println(classAttrValue);

        WebElement searchElement = moduleElement.findElement(By.className("searchInput"));
        searchElement.sendKeys("hello");
    }
}
