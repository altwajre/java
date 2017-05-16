# sonarqube

> Install sonar

http://chapter31.com/2013/05/02/installing-sonar-source-on-mac-osx/

$ brew update
$ brew install sonar

> Launch sonar

$ sonar start
$ sonar stop
$ sonar console

## Java Project

> pom.xml - add jacoco plugin

```
      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.7.9</version>
        <executions>
          <execution>
            <id>default-prepare-agent</id>
            <goals>
              <goal>prepare-agent</goal>
            </goals>
          </execution>
          <execution>
            <id>default-report</id>
            <phase>prepare-package</phase>
            <goals>
              <goal>report</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```

> Run test includes code coverage

```
$ mvn clean package sonar:sonar
```

> SonarQube - code coverage

http://localhost:9000/projects
