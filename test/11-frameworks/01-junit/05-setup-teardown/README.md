# Test fixtures

https://github.com/junit-team/junit4/wiki/Test-fixtures

> support running junit 5, 4, 3

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
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
            <version>4.12.1</version>
          </dependency>
          <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.0.3</version>
          </dependency>
        </dependencies>
      </plugin>
```