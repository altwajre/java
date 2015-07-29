package com.company.app.infrastructure;

import java.util.Map;

/*
  A placeholder for driver information
 */
public class DriverInfo {
    private PlatformType platType;
    private Map<String, Object> driverProperties;
    public DriverInfo(PlatformType platType, Map<String, Object> driverProperties){
        this.platType = platType;
        this.driverProperties = driverProperties;
    }
    public PlatformType getPlatType() {
        return platType;
    }
    public void setPlatType(PlatformType platType) {
        this.platType = platType;
    }
    public Map<String, Object> getDriverProperties() {
        return driverProperties;
    }
    public void setDriverProperties(Map<String, Object> driverProperties) {
        this.driverProperties = driverProperties;
    }
}
