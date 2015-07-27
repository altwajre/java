package com.company.app.infrastructure;

import org.openqa.selenium.WebDriver;
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
    private static final Map<String, String> PropertyAdapter = new HashMap<String, String>() {{
        put("browser", CapabilityType.BROWSER_NAME);
        put("version", CapabilityType.VERSION);
        put("platform", CapabilityType.PLATFORM);
        put("name", "name");
        put("orientation", "deviceOrientation");
        put("size", "screenResolution");
    }};
    private DesiredCapabilities cap;
    public String name;

    public WebDriverBuilder(String platType, Map<String, Object> properties) {
        name = platType + " ui testing";
        cap = new DesiredCapabilities();

        for (Map.Entry<String, Object> property : properties.entrySet()) {
            if (PropertyAdapter.containsKey(property.getKey())) {
                String name = PropertyAdapter.get(property.getKey());
                String value = property.getValue().toString();
                System.out.print("#name: '" + name + "', value: '" + value + "'");
                cap.setCapability(name, value);
                this.name += ":" + property.getValue();
            } else {
                throw new RuntimeException("Property undefined");
            }
            System.out.print(", test name: '" + this.name + "'\n");
            cap.setCapability("name", this.name);
        }
        System.out.println(cap.getBrowserName());
    }

    public WebDriver createDriver() {
        System.out.println("##" + cap.getBrowserName() + " " + cap.getVersion());
        try {
            System.out.println("Creating web driver ");
//            URL url = new URL("http://" + Global.Config.getUsername() + ":" + Global.Config.getKey() + "@ondemand.saucelabs.com:80/wd/hub");
//            URL url = new URL("http://localhost:4444/wd/hub");
//            cap = DesiredCapabilities.firefox();
//            WebDriver driver = new RemoteWebDriver(
//                    url,
//                    cap);
//            return driver;

            WebDriver driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    DesiredCapabilities.firefox());
            return driver;
        } catch (java.net.MalformedURLException e) {
            System.out.println("Something happened instead of creating a web driver");
            e.printStackTrace();
            throw new RuntimeException("some error happened... idk what tho");
        }
    }
}

