# Performing multi-module plugin management

https://www.safaribooksonline.com/library/view/apache-maven-cookbook/9781785286124/ch09s06.html

> `parent` project

add pluginManagement section below

```
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.2</version>
          <configuration>
            <source>1.8</source>
            <target>1.8</target>
          </configuration>
        </plugin>
        <plugin>
          <groupId>org.codehaus.mojo</groupId>
          <artifactId>build-helper-maven-plugin</artifactId>
          <version>1.9.1</version>
          <executions>
            <execution>
              <id>add-resource</id>
              <phase>generate-resources</phase>
              <goals>
                <goal>add-resource</goal>
              </goals>
              <configuration>
                <resources>
                  <resource>
                    <directory>src/resources/xml</directory>
                    <targetPath>xml</targetPath>
                  </resource>
                  <resource>
                    <directory>src/resources/json</directory>
                    <targetPath>json</targetPath>
                    <includes>
                      <include>include.json</include>
                    </includes>
                    <excludes>
                      <exclude>exclude.json</exclude>
                    </excludes>
                  </resource>
                </resources>
              </configuration>
            </execution>
          </executions>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```

> `child` project

```
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>build-helper-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

## Build

> build at `parent` folder `mvn clean test`

> build output

```
[INFO] Copying 2 resources to xml
[INFO] Copying 1 resource to json
```

> list copied files

```
$ ls child/target/classes/xml
one.xml  two.xml

$ ls child/target/classes/json
include.json
```
