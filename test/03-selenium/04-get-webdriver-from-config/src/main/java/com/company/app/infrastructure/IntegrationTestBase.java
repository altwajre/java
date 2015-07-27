package com.company.app.infrastructure;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

/**
 * Base class for all integration tests.  Does setup work like initializing and shutting down
 * WebDrivers.  If a child class uses the @Factory annotation on its constructor referencing
 * a data provider defined here, testNG will automatically create a separate instance of the
 * child for every platform defined in the platforms.yml file.
 */
public abstract class IntegrationTestBase {
    protected WebDriver driver;
    protected WebDriverBuilder driverFactory;
    private static int x = 0;
    protected String id;

    private volatile boolean cleanedUp = false;

    public IntegrationTestBase(WebDriverBuilder driverFactory) {
        this.driverFactory = driverFactory;
        this.id = driverFactory.name + (x++);
    }

    public String getName() {
        return id;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " running on " + getName();
    }

    @BeforeClass(alwaysRun = true)
    public void openClient() {
        this.driver = driverFactory.createDriver();

        //run cleanup even on control-C
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                IntegrationTestBase.this.closeClient();
            }
        }));
    }

    @AfterClass(alwaysRun = true)
    public synchronized void closeClient() {
        if (!cleanedUp) {
            cleanedUp = true;
            driver.quit();
        }
    }

    @DataProvider(name = "tabletPlatforms", parallel = true)
    public static Object[][] createTabletData() {
        return getWebDriversFromConfig("tablet");
    }

    @DataProvider(name = "mobilePlatforms", parallel = true)
    public static Object[][] mobilePlatformDataProvider() {
        return getWebDriversFromConfig("mobile");
    }

    @DataProvider(name = "desktopPlatforms", parallel = true)
    public static Object[][] createDesktopData() {
        return getWebDriversFromConfig("desktop");
    }

    protected static Object[][] getWebDriversFromConfig(String platType) {
        List<Map<String, Object>> platforms = Global.Platform.getPlatformsOfType(platType);
        System.out.println("##platforms.size()" + platforms.size());

        Object[][] ret = new Object[platforms.size()][1];

        for (int i = 0; i < platforms.size(); i++) {
            ret[i][0] = new WebDriverBuilder(platType, platforms.get(i));
        }

        return ret;
    }
}
