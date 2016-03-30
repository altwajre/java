package com.company.test.moduleobject;

import com.company.test.common.PlatformType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileDomainSearchBannerModule implements SitecoreModule {
    WebDriver driver;
    @FindBy(id = "domain-name-input")
    WebElement inputBox;
    WebElement searchButton;
    public MobileDomainSearchBannerModule(WebDriver driver, PlatformType platformType){
        this.driver = driver;
//        inputBox = driver.findElement(By.id("domain-name-input"));
        searchButton = driver.findElement(By.className("btn-primary"));
    }

    @Override
    public void run() {
        System.out.println("### MobileDomainSearchBannerModule.run()");
        searchDomain();
    }

    public void searchDomain(){
        inputBox.sendKeys("mobile");
//        searchButton.submit();
    }
}
