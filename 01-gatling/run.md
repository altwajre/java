# Run Tests

> Intellij

example project is at 00-rest-services/gatling-maven

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

- run all tests

```
mvn clean gatling:test
mvn clean gatling:execute
```

- run one test

```
mvn clean gatling:test -Dgatling.simulationClass=com.company.app.BasicSimulation
```

> Command line

~/gatling/bin/gatling.sh -sf . -m

- go to the simulation directory
$ cd 00-gatling-maven/src/test/scala/com/company/app
$ ~/gatling/bin/gatling.sh -sf . -m
- avoid questions, run without `-m`
$ ~/gatling/bin/gatling.sh -sf .
