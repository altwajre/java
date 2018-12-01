# intellij maven

## Create new project

1, Maven, checkout `Create from archetype`
2, click Add Archetype…
io.gatling.highcharts
gatling-highcharts-maven-archetype
3.0.0
3, select `gatling-highcharts-maven-archetype:3.0.0`

## Run

> intellij

right click `Engine` and click `Run Engine`

> maven command line

```
mvn gatling:test             // bound to test phase
mvn gatling:integration-test // bound to integration-test phase
```

## Note

Ensure Engine is runnable, need to delete target after run tests from command line

## Configuration

https://gatling.io/docs/2.3/general/configuration/

> Runs in mute mode

/resources/gatling.conf

```
mute = true
```

## Maven Plugin

https://gatling.io/docs/2.3/extensions/maven_plugin/

### Including / excluding simulations when running multiple simulations

```
<configuration>
  <runMultipleSimulations>true</runMultipleSimulations>
</configuration>
```

```
<configuration>
  <runMultipleSimulations>true</runMultipleSimulations>
  <includes>
    <param>my.package.MySimu1</param>
    <param>my.package.MySimu2</param>
  </includes>
</configuration>
```

```
<configuration>
  <runMultipleSimulations>true</runMultipleSimulations>
  <excludes>
    <param>my.package.MySimuNotToRun</param>
  </excludes>
</configuration>
```
