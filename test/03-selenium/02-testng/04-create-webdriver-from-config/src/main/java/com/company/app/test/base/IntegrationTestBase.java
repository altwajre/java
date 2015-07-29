package com.company.app.test.base;

import com.company.app.infrastructure.Consts;
import com.company.app.infrastructure.DriverInfo;
import com.company.app.infrastructure.Global;
import com.company.app.infrastructure.PlatformType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class IntegrationTestBase {
    protected WebDriver driver;
    protected DriverInfo driverInfo;

    public IntegrationTestBase(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }

    @BeforeClass(alwaysRun = true)
    public void openClient() {
        DesiredCapabilities cap = new DesiredCapabilities();
        // platforms.yml driver key should map to Capability name such as CapabilityType.BROWSER_NAME
        for (Map.Entry<String, Object> property : driverInfo.getDriverProperties().entrySet()) {
            String capName = property.getKey().toString();
            String capValue = property.getValue().toString();
            cap.setCapability(capName, capValue);
            String testName = driverInfo.getPlatType() + ":" + property.getValue();
            cap.setCapability("name", testName);
        }

        try{
            URL url = null;
            if(driverInfo.getPlatType() == PlatformType.local){
                url = new URL(Consts.URL_LOCAL);
            }
            else {
                url = new URL(Consts.URL_SAUCELAB);
            }

            WebDriver driver = new RemoteWebDriver(
                    url,
                    cap);
            this.driver = driver;
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("ERROR: unable to create driver");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeClient() {
        this.driver.quit();
    }

    @DataProvider(name = "tabletWebDrivers", parallel = true)
    public static Object[][] createTabletTestData() {
        return getWebDriversFromConfig(PlatformType.tablet);
    }

    @DataProvider(name = "mobileWebDrivers", parallel = true)
    public static Object[][] createMobileTestData() {
        return getWebDriversFromConfig(PlatformType.mobile);
    }

    @DataProvider(name = "localWebDrivers", parallel = true)
    public static Object[][] createLocalTestData() { return getWebDriversFromConfig(PlatformType.local); }

    @DataProvider(name = "desktopWebDrivers", parallel = true)
    public static Object[][] createDesktopTestData() {
        return getWebDriversFromConfig(PlatformType.desktop);
    }

    protected static Object[][] getWebDriversFromConfig(PlatformType platType) {
        List<Map<String, Object>> drivers = Global.Platforms.getDriversFromPlatform(platType);
        Object[][] ret = new Object[drivers.size()][1];
        for (int i = 0; i < drivers.size(); i++) {
            Map<String, Object> driverProperties = drivers.get(i); // platform: {browser: safari, version: 6, platform: OSX 10.8}
            ret[i][0] = new DriverInfo(platType, driverProperties);
        }
        return ret;
    }
}
