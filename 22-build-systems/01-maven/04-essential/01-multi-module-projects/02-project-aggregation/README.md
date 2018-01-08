# Project Aggregation

https://www.safaribooksonline.com/library/view/apache-maven-cookbook/9781785286124/ch09s03.html

## Create projects

> parent

- create maven project with pom file only, `<packaging>pom</packaging>`

> child

- in pom, specified below

```
    <groupId>com.company.app</groupId>
    <version>1.0-SNAPSHOT</version>
    <artifactId>child</artifactId>
    <packaging>jar</packaging>
```

- no `parent` element in pom file

## Build

> build at `parent` folder `mvn clean package`

## How it works

> parent

- add the module section and specify the child

```
  <modules>
    <module>child</module>
  </modules>
```

> child

- specified the child project as a module in the aggregator pom
- when the aggregator project is built, it builds the child project in turn
- word `Reactor` is in the Maven output, `Reactor` execute a goal on a set of modules
