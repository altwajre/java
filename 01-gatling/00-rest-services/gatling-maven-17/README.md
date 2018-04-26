# Gatling Intellij debug

https://gatling.io/docs/current/extensions/maven_archetype/

note: mvn clean gatling:execute does not work due to `<gatling-plugin.version>2.2.4</gatling-plugin.version>`

## Run Test

> mvn run

```
mvn clean gatling:execute
```

> debug in intellij

- disable runMultipleSimulations

comment out runMultipleSimulations

```
<configuration>
  <!--<runMultipleSimulations>true</runMultipleSimulations>-->
</configuration>
```

or change true to false

```
<configuration>
  <runMultipleSimulations>false</runMultipleSimulations>
</configuration>
```

Right click `Engine`, select test to run

## modularize scenarios

modularize files

```
/scenarios
/simulation
```

## Create project

- create project

```
$ mvn archetype:generate
filter: type gatling
archetype: select 1
version: 13
...
```

- gatling plugin

```
  <plugin>
    <groupId>io.gatling</groupId>
    <artifactId>gatling-maven-plugin</artifactId>
    <version>${gatling-plugin.version}</version>
  </plugin>
```

- remove execution goal, so `mvn clean install` does not run perf tests

https://stackoverflow.com/questions/38896841/skip-gatling-tests-when-building-release

```
    <executions>
      <execution>
        <id>getUsers</id>
        <goals>
          <goal>execute</goal>
        </goals>
        <configuration>
          <!--<simulationClass>com.company.app.LoadTest</simulationClass>-->
        </configuration>
      </execution>
    </executions>
```
