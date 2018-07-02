# Print Pom.xml Variables

> pom.xml

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-antrun-plugin</artifactId>
        <version>1.8</version>
        <executions>
          <execution>
            <phase>validate</phase>
            <goals>
              <goal>run</goal>
            </goals>
            <configuration>
              <tasks>
                <echo>Displaying pom.xml variables</echo>
                <echo>project.artifactId: ${project.artifactId}</echo>
              </tasks>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

> `mvn validate`

Print out display following

```
main:
     [echo] Displaying pom.xml variables
     [echo] project.artifactId: print-pom-variables
```
