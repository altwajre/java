# Running a web project with Tomcat 

> Project Type

`maven-archetype-webapp`

> Build

```
$ mvn clean package
```

> Run

```
$ mvn org.apache.tomcat.maven:tomcat7-maven-plugin:run
```

> Test

http://localhost:8080/run-webapp-with-tomcat/

## mvn tomcat7:run

> Add jetty pluginGroup to `.m2/settings.xml`

```
  <pluginGroups>
    <pluginGroup>org.apache.tomcat.maven</pluginGroup>
  </pluginGroups>
```

> Run

```
$ mvn tomcat7:run
```

> Test

http://localhost:8080/run-webapp-with-tomcat/

> Alternative Run (goals)

```
$ mvn jetty:run-war
$ mvn jetty:run-exploded
```
