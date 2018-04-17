# Gatling Intellij debug

https://gatling.io/docs/current/extensions/maven_archetype/

- create project

```
$ mvn archetype:generate
filter: type gatling
archetype: select 1
version: 13
...
```

## Run Test

- mvn

```
mvn clean gatling:execute
mvn clean install
```

- intellij

Right click `Engine`, click `Run`
