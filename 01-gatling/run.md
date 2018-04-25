# Run Tests

> Intellij

example project is at 00-rest-services/gatling-maven

- within intellij

Right click `Engine`, click `Run Engine`

- mvn

```
mvn clean gatling:execute
```

> Command line

- go to the simulation directory
$ cd 00-gatling-maven/src/test/scala/com/company/app
$ ~/gatling/bin/gatling.sh -sf . -m
- avoid questions, run without `-m`
$ ~/gatling/bin/gatling.sh -sf .
