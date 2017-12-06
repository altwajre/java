# hibernate kb

## Issues

> Could not load requested class : com.mysql.cj.jdbc.Driver

add following to pom.xml

```
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>6.0.6</version>
    </dependency>
```
