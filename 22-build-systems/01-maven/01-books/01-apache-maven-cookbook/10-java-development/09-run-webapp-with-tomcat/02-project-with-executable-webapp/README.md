# Project with executable webapp 

> pom.xml

```
    <plugins>
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.1</version>
        <executions>
          <execution>
            <id>tomcat-run</id>
            <goals>
              <goal>exec-war-only</goal>
            </goals>
            <phase>package</phase>
            <configuration>
              <path>/</path>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
```

> Build

```
$ mvn clean package
```

> Run

```
$ java -jar project-with-executable-webapp-1.0-SNAPSHOT-war-exec.jar
```

> Test

Do not know how to visit the webapp
