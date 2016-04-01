package com.company.test.moduleobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DesktopDomainSearchBannerModule implements SitecoreModule {
    WebDriver driver;

//    @FindBy(id = "domain-name-input")
    WebElement inputBox;

    WebElement searchButton;
    public DesktopDomainSearchBannerModule(WebDriver driver){
        this.driver = driver;
        inputBox = driver.findElement(By.id("domain-name-input"));
        searchButton = driver.findElement(By.className("btn-primary"));
    }

    @Override
    public void run() {
        System.out.println("### DesktopDomainSearchBannerModule.run()");
        searchDomain();
    }

    public void searchDomain(){
        inputBox.sendKeys("desktop");
//        searchButton.submit();
    }
}
