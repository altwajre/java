# maven-antrun-plugin

https://maven.apache.org/guides/mini/guide-using-ant.html

> pom.xml - execute bash command before and after integration test

```
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
            <exec executable="bash"
                  dir="${project.build.directory}"
                  spawn="false">
              <arg value="-c"/>
              <arg value="mkdir -p before"/>
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
          <target>
            <exec executable="bash"
                  dir="${project.build.directory}"
                  spawn="false">
              <arg value="-c"/>
              <arg value="mkdir -p after"/>
            </exec>
          </target>
        </configuration>
      </execution>
    </executions>
  </plugin>
```

## Intellij

> Test Run

Click `Maven Projects` on the right border, expand Lifecycle, click `verify` to run integration tests after unit tests

> Result

`before` and `after` folders are created under `target` folder
