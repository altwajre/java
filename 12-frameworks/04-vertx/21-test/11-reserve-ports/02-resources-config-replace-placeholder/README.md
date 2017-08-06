# replace placeholder in resources config

We use the build-helper-maven-plugin (a plugin to know if you are often using Maven) to pick up a free port. 
Once found, the plugin assigns the http.port variable to the picked port. 
We execute this plugin early in the build (during the process-sources phase), 
so we can use the http.port variable in the other plugin. This was for the first step.

Instructs Maven to filter resources from the src/test/resources directory. 
Filter means replacing placeholders by actual values. 
That’s exactly what we need as we now have the http.port variable. 
So create the src/test/resources/config.json file with the following content:

{
  "http.port": ${http.port}
}

> pom.xml

```
  <build>
    <testResources>
      <testResource>
        <directory>src/test/resources</directory>
        <filtering>true</filtering>
      </testResource>
    </testResources>

    <plugins>
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

    </plugins>
  </build>
```

## Intellij

> Test Run

Click `Maven Projects` on the right border, expand Lifecycle, click `test` to run integration tests after unit tests

> Result - generated config

`target/test-classes/config.json`

```
{
  "http.port": 59914
}
```
