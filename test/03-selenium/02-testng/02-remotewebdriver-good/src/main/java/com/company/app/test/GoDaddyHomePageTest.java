package com.company.app.test;

import com.company.app.binding.DesktopDomainSearchBannerModule;
import com.company.app.testbase.Helper;
import com.company.app.testbase.ModuleTest;
import com.google.inject.Guice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Test
public class GoDaddyHomePageTest {
    @Test(groups = {"only"})
    public void findElement_Test(){
        Helper.getTestName("a");
//        WebDriver driver = null;
//        try {
//            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        driver.get("http://www.godaddy.com");
//        System.out.println("findElement one module");
//        WebElement webElement = driver.findElement(By.cssSelector(".main-content > div[class~='vsc-domain-search-banner-module']"));
//        String classAttrValue = webElement.getAttribute("class");
//        System.out.println(classAttrValue);
//
//        System.out.println("\nfindElement all modules");
//        List<WebElement> webElements = driver.findElements(By.cssSelector(".main-content > div[class*='vsc']"));
//        System.out.println(webElements.size());
//        for(int i = 0; i < webElements.size(); i++){
//            String attrValue = webElements.get(i).getAttribute("class");
//            System.out.println(attrValue);
//        }
//
//        Guice.createInjector(new DesktopDomainSearchBannerModule()).getInstance(ModuleTest.class).run(webElement);
    }
}
