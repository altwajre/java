# Run 

> Project Type

`maven-archetype-webapp`

> Build

```
$ mvn clean package

$ ls target/
classes		maven-archiver	war-project	war-project.war
```

> Run

```
$ mvn org.eclipse.jetty:jetty-maven-plugin:run
```

> Test

http://localhost:8080/

## mvn jetty:run

> Add jetty pluginGroup to `.m2/settings.xml`

```
  <pluginGroups>
    <pluginGroup>org.eclipse.jetty</pluginGroup>
  </pluginGroups>
```

> Run

```
$ mvn jetty:run
```

> Test

http://localhost:8080/

> Alternative Run (goals)

```
$ mvn jetty:run-war
$ mvn jetty:run-exploded
```
