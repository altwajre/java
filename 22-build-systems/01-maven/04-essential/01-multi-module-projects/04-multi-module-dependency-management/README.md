# Performing multi-module dependency management

> `parent` project

update the `pom` file as below

```
  <groupId>com.company.app</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>
  <modules>
    <module>child</module>
  </modules>
...
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```

> `child` project

update the `pom` file as below - in <dependency>, omit version and scope information so they are inherited from parent

```
    <parent>
        <artifactId>parent</artifactId>
        <groupId>com.company.app</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
...
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>
```

## Build

- build at `child` folder `mvn clean test`
