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

google `maven clean plugin documentation` or click `clean` link at https://maven.apache.org/plugins/

https://maven.apache.org/plugins/maven-clean-plugin/

### Goals

> clean

`clean` goal properties are listed below

https://maven.apache.org/plugins/maven-clean-plugin/clean-mojo.html

## configuring-plugins

https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Configuring_Build_Plugins

configuration, executions (phase and goal) 

> bind `<configuration>` to `<phase>compile</phase>` and execute `<goal>javadoc</goal>`

since `javadoc` is not in super pom, need to specify `<build><plugins><plugin><artifactId>maven-javadoc-plugin` before
`<pluginManagement>`

pom.xml

```
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-javadoc-plugin</artifactId>
        <groupId>org.apache.maven.plugins</groupId>
        <version>2.10.1</version>
      </plugin>
    </plugins>
    <pluginManagement>
      <plugins>
        <plugin>
          <artifactId>maven-javadoc-plugin</artifactId>
          <configuration>
            <header>myheader</header>
            <footer>myfooter</footer>
          </configuration>
          <executions>
            <execution>
              <phase>compile</phase>
              <goals>
                <goal>javadoc</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```

mvn phases

```
mvn clean compile
```

