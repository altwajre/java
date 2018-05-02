# Maven Plugin

https://gatling.io/docs/2.3/extensions/maven_plugin/

## Including / excluding simulations when running multiple simulations

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

## Running the Plugin

```
mvn gatling:test             // bound to test phase
mvn gatling:integration-test // bound to integration-test phase
```

## github

https://github.com/gatling/gatling-maven-plugin-demo
