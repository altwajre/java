//package com.company.app.test;
//
//import com.company.app.infrastructure.Global;
//import com.company.app.infrastructure.WebDriverBuilder;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import java.util.List;
//import java.util.Map;
//
//public class CreateCapFromConfigTest {
//    private WebDriver driver;
//    private WebDriverBuilder driverBuilder;
//
//    @DataProvider
//    public static Object[][] desktopData(){
//        String platType = "desktop";
//        List<Map<String, Object>> platforms = Global.Platform.getPlatformsOfType(platType);
//        Object[][] ret = new Object[platforms.size()][1];
//        for(int i = 0; i < platforms.size(); i++){
//            ret[i][0] = new WebDriverBuilder(platType, platforms.get(i));
//        }
//        return ret;
//    }
//
//    @Factory(dataProvider = "desktopData")
//    public CreateCapFromConfigTest(WebDriverBuilder driverBuilder) {
//        this.driverBuilder = driverBuilder;
//    }
//
//    @BeforeClass(alwaysRun = true)
//    public void openClient(){
//        this.driver = driverBuilder.createDriver(); // <-- read info from yml to RemoteWebDriver DesiredCapabilities
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void closeClient(){
//        this.driver.quit();
//    }
//
//    @Test(priority = 1)
//    public void verifyTitle_test(){
//        driver.get("http://www.google.com");
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Cheese!");
//        element.submit();
//        System.out.println("#" + driver.getTitle());
//        Assert.assertEquals(driver.getTitle(), "Google");
//        driver.quit();  // uncomment out it to close browser
//    }
//}
