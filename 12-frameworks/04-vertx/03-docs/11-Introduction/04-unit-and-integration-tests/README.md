# Unit and Integration Tests

> Unit tests

Unit tests ensure that one component of your application, generally a class in the Java world, behaves as expected.
The application is not tested as a whole, but pieces by pieces.

> Integration tests

Integration tests are more black box in the sense that the application is started and tested generally externally.

## Unit tests

> pom.xml

```
    <dependency>
      <groupId>io.vertx</groupId>
      <artifactId>vertx-unit</artifactId>
      <version>3.4.1</version>
      <scope>test</scope>
    </dependency>
```

> Tests

com.company.app.unit.WhiskyVerticleTest

## Integration tests

> Tasks

- Reserve a free port - get a free port on which the application can listen, and we need to inject this port
- Generate application configuration - write a JSON file configuring the application HTTP port to this port
- Start the application - launch the application in a background process
- Execute the integration tests - run the integration tests
- Stop the application - stop the application after test executed

### pom.xml

> Fat Jar

<outputFile>${project.build.directory}/${project.artifactId}-${project.version}-fat.jar</outputFile>
need to match `Start and stop the application` <arg value="${project.artifactId}-${project.version}-fat.jar"/>

```
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.4.3</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <transformers>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <manifestEntries>
                    <Main-Class>io.vertx.core.Launcher</Main-Class>
                    <Main-Verticle>com.company.app.WhiskyVerticle</Main-Verticle>
                  </manifestEntries>
                </transformer>
              </transformers>
              <artifactSet/>
              <outputFile>${project.build.directory}/${project.artifactId}-${project.version}-fat.jar</outputFile>
            </configuration>
          </execution>
        </executions>
      </plugin>
```

> Pick up a free port plugin. Once found, the plugin assigns the http.port variable to the picked port

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

> Filter resources from `src/test/resources`, replace placeholders by actual values

```
    <testResources>
      <testResource>
        <directory>src/test/resources</directory>
        <filtering>true</filtering>
      </testResource>
    </testResources>
```

> Config file, `${http.port}` is the (default) Maven syntax for filtering; replace `${http.port}` by the selected port

```
src/test/resources/config.json
```

> Start and stop the application

```
      <!-- We use the maven-antrun-plugin to start the application before the integration tests and stop them afterward -->
      <plugin>
        <artifactId>maven-antrun-plugin</artifactId>
        <version>1.8</version>
        <executions>
          <execution>
            <id>start-vertx-app</id>
            <phase>pre-integration-test</phase>
            <goals>
              <goal>run</goal>
            </goals>
            <configuration>
              <target>
                <!--
                Launch the application as in 'production' using the fatjar.
                We pass the generated configuration, configuring the http port to the picked one
                -->
                <exec executable="${java.home}/bin/java"
                      dir="${project.build.directory}"
                      spawn="true">
                  <arg value="-jar"/>
                  <arg value="${project.artifactId}-${project.version}-fat.jar"/>
                  <arg value="-conf"/>
                  <arg value="${project.build.directory}/test-classes/config.json"/>
                </exec>
              </target>
            </configuration>
          </execution>
          <execution>
            <id>stop-vertx-app</id>
            <phase>post-integration-test</phase>
            <goals>
              <goal>run</goal>
            </goals>
            <configuration>
              <!--
                Kill the started process.
                Finding the right process is a bit tricky. Windows command in in the windows profile (below)
                -->
              <target>
                <exec executable="bash"
                      dir="${project.build.directory}"
                      spawn="false">
                  <arg value="-c"/>
                  <arg value="ps ax | grep -i '${project.artifactId}' | awk 'NR==1{print $1}' | xargs kill -SIGTERM"/>
                </exec>
              </target>
            </configuration>
          </execution>
        </executions>
      </plugin>
```

> Execute integration tests

```
      <!-- Configure the integration-test plugin and pass the 'http.port' as system property -->
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

### Tests

com.company.app.integration.WhiskyVerticleRestIT

> Run

```
$ mvn clean verify
```
