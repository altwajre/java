package com.company.app

import org.json.JSONObject
import spock.lang.Specification

class MyTest extends Specification {

    def "load config"() {

        given:
        def x
        // Get file from resources folder
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String env = System.getenv("TEST_ENV");
        System.out.println(env);

        String configPath = "config/config." + env + ".json";
        InputStream inputStream = classLoader.getResourceAsStream(configPath);

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject config = new JSONObject(responseStrBuilder.toString());
        System.out.println(config.toString());
        System.out.println(config.get("test-settings"));

        String url = config.getJSONObject("test-settings").get("url");
        System.out.println(url);

        when:
        x = true

        then:
        x
    }
}
/*
Output:
dev
{"test-settings":{"url":"http://www.dev-company.com"}}
{"url":"http://www.dev-company.com"}
http://www.dev-company.com
 */
