# Maven JAR Plugin

https://maven.apache.org/plugins/maven-jar-plugin/

## jar:jar (plugin:goal)

> Find out `jar` plugin info from view all plugins include super pom

```
`mvn help:effective-pom` 
```

```
      <plugin>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.4</version>
        <executions>
          <execution>
            <id>default-jar</id>
            <phase>package</phase>
            <goals>
              <goal>jar</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```

### Specify plugin goal properties

> command line

`mvn jar:jar -Djar.finalName=test -Djar.forceCreation=true`

> pom.xml

```
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>2.4</version>
          <configuration>
            <finalName>pomTest</finalName>
            <forceCreation>true</forceCreation>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```

`mvn compile jar:jar`
