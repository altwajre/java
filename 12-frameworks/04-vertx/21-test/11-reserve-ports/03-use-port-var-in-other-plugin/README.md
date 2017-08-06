# Use picked port variable in other plugin

> http.port

1. Use the `build-helper-maven-plugin` to pick up a free port.
Once found, the plugin assigns the picked port to the `http.port` variable.

```
  <plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>build-helper-maven-plugin</artifactId>
    <version>1.9.1</version>
    <executions>
      <execution>
        <id>reserve-network-port</id>
        <goals>
          <goal>reserve-network-port</goal>
        </goals>
        <phase>process-sources</phase>
        <configuration>
          <portNames>
            <portName>http.port</portName>
          </portNames>
        </configuration>
      </execution>
    </executions>
  </plugin>
```

2. This plugin is executed early in the build (during the process-sources phase), 
so the `http.port` variable is assigned to `${http.port}` as `system property` in the other plugin.

```
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>2.18.1</version>
    <executions>
      <execution>
        <goals>
          <goal>integration-test</goal>
          <goal>verify</goal>
        </goals>
        <configuration>
          <systemProperties>
            <http.port>${http.port}</http.port>
          </systemProperties>
        </configuration>
      </execution>
    </executions>
  </plugin>
```

3. Integration test can use the `system property` `http.port`

```
  @Test
  public void testIntegration() {
    Integer port = Integer.getInteger("http.port", 8080);
    System.out.println("integration: port="+port);

    String portProperty = System.getProperty("http.port");
    System.out.println("System.getProperty('http.port')=" + portProperty);
  }
```

> config.json

pom.xml

```
<testResources>
  <testResource>
    <directory>src/test/resources</directory>
    <filtering>true</filtering>
  </testResource>
</testResources>
```

Instructs Maven to filter resources from the src/test/resources directory. 
Filter means replacing placeholders by actual values. 
That’s exactly what we need as we now have the http.port variable. 
So create the src/test/resources/config.json file with the following content:

```
{
  "http.port": ${http.port}
}
```

## Intellij 

> Test Run

Click `Maven Projects` on the right border, expand Lifecycle, click `verify` to run integration tests after unit tests

> Result

Unit Test

```
Running com.company.app.unit.AssignPortToVariableTest
unit: port=56899
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.044 sec
```

Generated config

`target/test-classes/config.json`

```
{
  "http.port": 56892
}
```

Integration Test

```
Running com.company.app.integration.UsePortVariableInOtherPluginIT
integration: port=56892
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec - in com.company.app.integration.UsePortVariableInOtherPluginIT
```
