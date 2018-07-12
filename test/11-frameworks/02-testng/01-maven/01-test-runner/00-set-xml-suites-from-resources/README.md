# set xml suites - run all tests from command line

## Run TestRunner

```
      <plugin>
        <!-- Build an executable JAR -->
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.0.2</version>
        <configuration>
          <archive>
            <manifest>
              <mainClass>com.company.app.TestRunner</mainClass>
            </manifest>
          </archive>
        </configuration>
      </plugin>
```

## pom.xml Package up the application into a big fat jar

```
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.3</version>
        <configuration>
        </configuration>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

```

## Compile

> `mvn clean package`

## Run

```
java -jar target/set-xml-suites-from-resources-1.0-SNAPSHOT.jar
export TEST_SUITE=test-p0.xml && java -jar target/set-xml-suites-from-resources-1.0-SNAPSHOT.jar
```

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
