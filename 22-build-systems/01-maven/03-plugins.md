# Plugins

https://maven.apache.org/plugins/

https://maven.apache.org/guides/mini/guide-configuring-plugins.html

```
Plugin = class
Goal = method
`mvn plugin:goal` = `mvn clean:clean`
```

> View all plugins include super pom

```
`mvn help:effective-pom` 
```

Find out `jar` plugin info from super pom

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

## plugin documentation

search `maven clean plugin documentation`

https://maven.apache.org/plugins/maven-clean-plugin/

### Goals

> clean

`clean` goal properties are listed below

https://maven.apache.org/plugins/maven-clean-plugin/clean-mojo.html
