package com.company.test.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

public class DriverFactory {
    private String name = "";
    private PlatformType platformType;
    private DesiredCapabilities cap;

    public DriverFactory(PlatformType platformType, Map<String, Object> driverProperties){
        this.platformType = platformType;

        cap = new DesiredCapabilities();
        for (Map.Entry<String, Object> property : driverProperties.entrySet()) {
            String capName = property.getKey().toString();
            String capValue = property.getValue().toString();
            cap.setCapability(capName, capValue);
            name += "," + property.getValue().toString();
        }
        cap.setCapability("name", name);
    }
    public PlatformType getPlatformType(){
        return platformType;
    }
    public String getName(){
        return name;
    }
    public void setDescription(String description) {
        this.name = description + " running on " + this.name;
    }

    public WebDriver createDriver(){
        try {
            return new RemoteWebDriver(new URL(Consts.URL_SAUCELAB), cap);
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR: Unable to create driver");
        }
    }
}
