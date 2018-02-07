package com.company.app;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/*
https://www.mkyong.com/java/java-properties-file-examples/
 */
public class App
{
    public static void main( String[] args ) throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config/config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        System.out.println("# Print one");
        System.out.println(properties.getProperty("database"));

        Enumeration<?> propertyNames = properties.propertyNames();

        System.out.println("# Print all");
        while (propertyNames.hasMoreElements()) {
            String key = (String)propertyNames.nextElement();
            String value = properties.getProperty(key);
            System.out.println("Key=" + key + ", Value=" + value);
        }

    }
}
/*
output:
# Print one
localhost
# Print all
Key=dbpassword, Value=password
Key=database, Value=localhost
Key=dbuser, Value=tom
 */
