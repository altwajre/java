# simple

maven-failsafe-plugin

> pom.xml

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
          </execution>
        </executions>
      </plugin>
```

## Intellij

> Test Run

Click `Maven Projects` on the right border, expand Lifecycle, click `verify` to run integration tests after unit tests

> Result

Unit Test

```
Running com.company.app.unit.UnitTest
testUnit
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.013 sec
```

Integration Test

```
Running com.company.app.integration.IntegrationIT
testIntegration
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec - in com.company.app.integration.IntegrationIT
```
