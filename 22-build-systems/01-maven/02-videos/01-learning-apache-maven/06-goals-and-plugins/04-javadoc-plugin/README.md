# Javadoc Plugin

https://maven.apache.org/plugins/maven-javadoc-plugin/

## javadoc:javadoc (plugin:goal)

`mvn javadoc:javadoc`

### Specify plugin goal parameters

> command line

`mvn javadoc:javadoc -Dheader=myheader -Dfooter=myfooter`

> pom.xml - bind `<configuration>` to `<phase>compile</phase>` and execute `<goal>javadoc</goal>`

since `javadoc` is not in super pom, need to specify `<build><plugins><plugin><artifactId>maven-javadoc-plugin` before
`<pluginManagement>`

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

```
mvn clean compile
```
