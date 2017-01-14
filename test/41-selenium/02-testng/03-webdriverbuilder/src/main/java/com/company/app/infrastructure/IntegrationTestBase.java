package com.company.app.infrastructure;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public abstract class IntegrationTestBase {
    protected WebDriver driver;
    protected WebDriverBuilder driverBuilder;
    public IntegrationTestBase(WebDriverBuilder driverBuilder) {
        this.driverBuilder = driverBuilder;
    }
    @BeforeClass(alwaysRun = true)
    public void openClient() { this.driver = driverBuilder.createDriver(); }

    @AfterClass(alwaysRun = true)
    public void closeClient() { this.driver.quit(); }
    @DataProvider(name = "tabletPlatforms", parallel = true)
    public static Object[][] createTabletData() {
        return getWebDriversFromConfig("tablet");
    }
    @DataProvider(name = "mobilePlatforms", parallel = true)
    public static Object[][] mobilePlatformDataProvider() {
        return getWebDriversFromConfig("mobile");
    }

    @DataProvider(name = "local", parallel = true)
    public static Object[][] createLocalData() {
        return getWebDriversFromConfig("local");
    }
    @DataProvider(name = "desktopPlatforms", parallel = true)
    public static Object[][] createDesktopData() {
        return getWebDriversFromConfig("desktop");
    }
    protected static Object[][] getWebDriversFromConfig(String platType) {
        Object[][] ret = null;
        if(platType == "local"){
            ret = new Object[1][1];
            ret[0][0] = new WebDriverBuilder("local", null);
        }
        else {
            List<Map<String, Object>> drivers = Global.Platform.getPlatformsOfType(platType);
            System.out.println("##platforms.size()" + drivers.size());
            ret = new Object[drivers.size()][1];
            for (int i = 0; i < drivers.size(); i++) {
                Map<String, Object> driverProperties = drivers.get(i); // platform: {browser: safari, version: 6, platform: OSX 10.8}
                System.out.println("#properties key browser: " + driverProperties.get("browser"));
                ret[i][0] = new WebDriverBuilder(platType, driverProperties);
            }
        }
        return ret;
    }
}
