# gatling docs

https://gatling.io/docs/current/general

## Run Test

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

> mvn run

```
mvn clean gatling:execute
```

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

## Configuration

https://gatling.io/docs/2.3/general/configuration/

> Runs in mute mode

/resources/gatling.conf

```
mute = true
```
