package com.company.app.infrastructure;

import java.util.List;
import java.util.Map;

/**
 * java object representation of data in platforms.yml (in root directory of project).
 * getPlatforms and setPlatforms are required by snakeyaml to populate the object with
 * data from the configuration file.
 */
public class Platforms {
    private Map<String, List<Map<String, Object>>> platforms;

    public Map getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Map<String, List<Map<String, Object>>> platforms) {
        this.platforms = platforms;
    }

    public List<Map<String, Object>> getPlatformsOfType(String platType) {
        if (platforms.containsKey(platType)) {
            return platforms.get(platType);
        } else {
            throw new RuntimeException("configuration file does not define platforms of type " + platType);
        }
    }
}
