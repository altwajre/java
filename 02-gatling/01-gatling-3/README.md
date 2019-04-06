# gatling docs

https://gatling.io/docs/current/general

https://gatling.io/docs/current/general/simulation_setup/

## Server

Launch `00-vertx-server` first

## Create maven project

```
1, checkout `Create from archetype`
2, click Add Archetype…
io.gatling.highcharts
gatling-highcharts-maven-archetype
3.0.0
3, select root `gatling-highcharts-maven-archetype`
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

## Configuration

https://gatling.io/docs/3.0/general/configuration/

> Runs in mute mode does NOT work in 3.0

/resources/gatling.conf

```
mute = true
```

## Note

http.warmup() is used for warming up Gatling itself
