# Generating an executable JAR

> `parent` project

- add following section

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.5</version>
        <configuration>
          <archive>
            <manifest>
              <addClasspath>true</addClasspath>
              <mainClass>com.company.app.App</mainClass>
            </manifest>
          </archive>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>2.9</version>
        <executions>
          <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
              <goal>copy-dependencies</goal>
            </goals>
            <configuration>
              <outputDirectory>${project.build.directory}</outputDirectory>
              <excludeArtifactIds>junit</excludeArtifactIds>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

## Run Executable JAR

> build - `mvn clean package`

> run the executable JAR

```
$ java -jar target/executable-jar-1.0-SNAPSHOT.jar
23:21:05.956 [main] INFO  com.company.app.App - Hello World
```
