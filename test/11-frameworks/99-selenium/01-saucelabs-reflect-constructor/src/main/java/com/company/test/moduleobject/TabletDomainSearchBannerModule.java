package com.company.test.moduleobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TabletDomainSearchBannerModule implements SitecoreModule {
    WebDriver driver;

//    @FindBy(id = "domain-name-input")
    WebElement inputBox;

    WebElement searchButton;
    public TabletDomainSearchBannerModule(WebDriver driver){
        this.driver = driver;
        inputBox = driver.findElement(By.id("domain-name-input"));
        searchButton = driver.findElement(By.className("btn-primary"));
    }

    @Override
    public void run() {
        System.out.println("### TabletDomainSearchBannerModule.run()");
        searchDomain();
    }

    public void searchDomain(){
        inputBox.sendKeys("tablet");
//        searchButton.submit();
    }
}
