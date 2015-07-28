package com.company.app.infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Takes settings for creating a web driver in the format in which
 * platforms.yml provides them, and converts those settings into the format that
 * RemoteWebDriver expects them to be in.  Stores those settings until the driver is needed
 * in order to conserve connections to saucelabs.
 */
public class WebDriverBuilder {
    private boolean isLocal = false;
    private DesiredCapabilities cap;
    public String name;

    public WebDriverBuilder(String platType, Map<String, Object> driverProperties) {
        name = platType + " ui testing";
        if (platType == "local") isLocal = true;
        else {
            cap = new DesiredCapabilities();
            for (Map.Entry<String, Object> property : driverProperties.entrySet()) {
                String name = property.getKey().toString();
                String value = property.getValue().toString();
                System.out.print("#name: '" + name + "', value: '" + value + "'");
                cap.setCapability(name, value);
                this.name += ":" + property.getValue();
                System.out.print(", test name: '" + this.name + "'\n");
                cap.setCapability("name", this.name);
            }
            System.out.println(cap.getBrowserName());
        }
    }

    public WebDriver createDriver() {
        try {
            System.out.println("Creating web driver ");
            WebDriver driver = null;
            if (isLocal) {
                // need to launch selenium server to create firefox driver.
                driver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        DesiredCapabilities.firefox());
            } else {
                URL url = new URL("http://" + Global.Config.getUsername() + ":" + Global.Config.getKey() + "@ondemand.saucelabs.com:80/wd/hub");
                driver = new RemoteWebDriver(
                        url,
                        cap);

            }

//            WebDriver driver = new HtmlUnitDriver();

            return driver;
        } catch (Exception e) {
            System.out.println("Something happened instead of creating a web driver");
            e.printStackTrace();
            throw new RuntimeException("some error happened... idk what tho");
        }
    }
}

