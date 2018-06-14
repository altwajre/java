# rest assured

## intellij

App.main()

## junit5

https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-running-unit-tests-with-maven/

need following for `mvn clean test` to work

```
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.19.1</version>
    <dependencies>
      <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-surefire-provider</artifactId>
        <version>1.0.1</version>
      </dependency>
      <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.0.3</version>
      </dependency>
    </dependencies>
  </plugin>
```
