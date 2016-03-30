package com.company.test.pageobject;

import com.company.test.common.PlatformType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GodaddyHomePage extends ModuleTestBase {
    public GodaddyHomePage(WebDriver driver, PlatformType platformType){
        this.driver = driver;
        this.platformType = platformType;
        String url = "https://www.godaddy.com";
        driver.get(url);
    }
    public void testModules(){
        System.out.print("\n### find all modules - ");
        List<WebElement> webElements = driver.findElements(By.cssSelector(".main-content > div[class*='vsc']"));
        System.out.println("count="+webElements.size());
        for(int i = 0; i < webElements.size(); i++){
            String classNames = webElements.get(i).getAttribute("class");
            performModuleTest(classNames.split(" ")[0]);
        }
    }
}
