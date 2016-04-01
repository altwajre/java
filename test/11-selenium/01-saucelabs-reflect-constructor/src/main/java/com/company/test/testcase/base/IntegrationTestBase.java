package com.company.test.testcase.base;

import com.company.test.common.Consts;
import com.company.test.common.DriverFactory;
import com.company.test.common.Global;
import com.company.test.common.PlatformType;
import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public abstract class IntegrationTestBase {
    protected WebDriver driver;
    protected DriverFactory driverFactory;
    private SauceREST sauceREST;

    private volatile boolean cleanedUp = false;

    public IntegrationTestBase(DriverFactory driverFactory){
        this.driverFactory = driverFactory;
        sauceREST = new SauceREST(Consts.SAUCELAB_USERNAME, Consts.SAUCELAB_KEY);
        driverFactory.setDescription(this.getClass().getSimpleName());
    }

    @BeforeClass(alwaysRun = true)
    public void openClient(){
        driver = driverFactory.createDriver();

        //run cleanup even on control-C
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                IntegrationTestBase.this.closeClient();
            }
        }));
    }

    @AfterClass(alwaysRun = true)
    public synchronized void closeClient(){
        if(!cleanedUp){
            cleanedUp = true;
            driver.quit();
        }
    }

    /**
     * Place a link in the jenkins report so that the user may see a video of
     * the session in saucelabs
     * If the test failed, tell sauceLabs to note that on the session
     */
    @AfterMethod
    public void logResults(ITestResult result) {
        String jobId = ((RemoteWebDriver) driver).getSessionId().toString();

        if (result.getStatus() == ITestResult.FAILURE) {
            sauceREST.jobFailed(jobId);
        }
        Reporter.log(sauceREST.getPublicJobLink(jobId));
    }

    @DataProvider(name = "desktopPlatforms", parallel = true)
    public static Object[][] createDesktopTestData() {
        return createWebDrivers(PlatformType.desktop);
    }

    @DataProvider(name = "tabletPlatforms", parallel = true)
    public static Object[][] createTabletTestData() {
        return createWebDrivers(PlatformType.tablet);
    }

    @DataProvider(name = "mobilePlatforms", parallel = true)
    public static Object[][] createMobileTestData() {
        return createWebDrivers(PlatformType.mobile);
    }

    /**
     * TestNG data providers need to return a two-dimensional array.  Refer to:
     * http://testng.org/javadoc/org/testng/annotations/DataProvider.html
     *
     * @param platType
     * @return
     *
     * n rows, and different driver on each row
     */
    private static Object[][] createWebDrivers(PlatformType platType){
        List<Map<String, Object>> drivers = Global.Platforms.getDrivers(platType);

        Object[][] ret = new Object[drivers.size()][1];

        for (int i = 0; i < drivers.size(); i++) {
            Map<String, Object> driverProperties = drivers.get(i);
            //Just create a driver builder that stores settings for the driver.  We will connect later
            ret[i][0] = new DriverFactory(platType, driverProperties);
        }

        return ret;
    }

}
