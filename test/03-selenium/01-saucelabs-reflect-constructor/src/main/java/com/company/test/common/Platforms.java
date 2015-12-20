package com.company.test.common;

import java.util.List;
import java.util.Map;

public class Platforms {
    private Map<String, List<Map<String, Object>>> platforms;

    public Map getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Map<String, List<Map<String, Object>>> platforms) {
        this.platforms = platforms;
    }

    public List<Map<String, Object>> getDrivers(PlatformType platType) {
        if (platforms.containsKey(platType.toString())) {
            return platforms.get(platType.toString());
        } else {
            throw new RuntimeException("configuration file does not define platforms of type " + platType);
        }
    }
}
