# Vert.x Simple Maven Verticle project

https://github.com/vert-x3/vertx-examples/tree/master/maven-verticles/maven-verticle

> pom.xml

```
<Main-Class>io.vertx.core.Launcher</Main-Class>
<Main-Verticle>com.company.app.HelloWorldVerticle</Main-Verticle>
```

```
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.4.3</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <transformers>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <manifestEntries>
                    <Main-Class>io.vertx.core.Launcher</Main-Class>
                    <Main-Verticle>com.company.app.HelloWorldVerticle</Main-Verticle>
                  </manifestEntries>
                </transformer>
              </transformers>
            </configuration>
          </execution>
        </executions>
      </plugin>
```

> Compile

```
$ mvn clean package
```

> Run

```
$ java -jar target/verticle-1.0-SNAPSHOT.jar
```

go to http://localhost:8080/
