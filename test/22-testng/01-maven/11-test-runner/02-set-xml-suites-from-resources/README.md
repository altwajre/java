# set xml suites - run all tests from command line

## pom.xml Package up the application into a big fat jar

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.3</version>
        <configuration>
          <createDependencyReducedPom>true</createDependencyReducedPom>
          <filters>
            <filter>
              <artifact>*:*</artifact>
              <excludes>
                <exclude>META-INF/*.SF</exclude>
                <exclude>META-INF/*.DSA</exclude>
                <exclude>META-INF/*.RSA</exclude>
              </excludes>
            </filter>
          </filters>
        </configuration>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <transformers>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <mainClass>com.company.app.App</mainClass>
                </transformer>
              </transformers>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

## Compile

> `mvn clean package`

## Run

> `$ java -jar target/app-1.0-SNAPSHOT.jar`

output:

```
[TestNG] Running:
  /Users/whan/Desktop/github/java-repo/7-2/java/test/22-testng/11-test-runner/01-run-all-tests/test-all.xml


===============================================
test-all
Total tests run: 2, Failures: 1, Skips: 0
===============================================
```

test result:

```
open ./test-output/index.html
```
