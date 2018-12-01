# gatling docs

https://gatling.io/docs/current/general

/gatling/gatling-core/src/test/scala/io/gatling/core/compile/CoreCompileTest.scala

## Server

Launch `00-vertx-server` first

## Create maven project

```
1, checkout `Create from archetype`
2, click Add Archetype…
io.gatling.highcharts
gatling-highcharts-maven-archetype
3.0.0
3, select `gatling-highcharts-maven-archetype:3.0.0`
```

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
mvn clean gatling:test
mvn clean gatling:execute
```

## modularize scenarios

modularize files

```
/scenarios
/simulation
```

## Configuration

https://gatling.io/docs/2.3/general/configuration/

> Runs in mute mode

/resources/gatling.conf

```
mute = true
```

## Note

http.warmup() is used for warming up Gatling itself
