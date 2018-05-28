# maven-antrun-plugin

https://maven.apache.org/guides/mini/guide-using-ant.html

> pom.xml - execute bash scripts before and after phases integration test

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

view all phases by using `$ mvn help:describe -Dcmd=deploy`, `pre-integration-test` and `post-integration-test` are listed

```
[INFO] --- maven-help-plugin:2.2:describe (default-cli) @ main ---
[INFO] 'deploy' is a phase corresponding to this plugin:
org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy

It is a part of the lifecycle for the POM packaging 'jar'. This lifecycle includes the following phases:
* validate: Not defined
* initialize: Not defined
* generate-sources: Not defined
* process-sources: Not defined
* generate-resources: Not defined
* process-resources: org.apache.maven.plugins:maven-resources-plugin:2.6:resources
* compile: org.apache.maven.plugins:maven-compiler-plugin:3.1:compile
* process-classes: Not defined
* generate-test-sources: Not defined
* process-test-sources: Not defined
* generate-test-resources: Not defined
* process-test-resources: org.apache.maven.plugins:maven-resources-plugin:2.6:testResources
* test-compile: org.apache.maven.plugins:maven-compiler-plugin:3.1:testCompile
* process-test-classes: Not defined
* test: org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test
* prepare-package: Not defined
* package: org.apache.maven.plugins:maven-jar-plugin:2.4:jar
* pre-integration-test: Not defined
* integration-test: Not defined
* post-integration-test: Not defined
* verify: Not defined
* install: org.apache.maven.plugins:maven-install-plugin:2.4:install
* deploy: org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy
```

## Intellij

> Test Run

Click `Maven Projects` on the right border, expand Lifecycle, click `verify` to run integration tests after unit tests

> Result

`before` and `after` folders are created under `target` folder
