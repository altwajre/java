# Project Inheritance

https://www.safaribooksonline.com/library/view/apache-maven-cookbook/9781785286124/ch09s02.html

Maven allows a number of elements specified in the parent pom file to be merged to the inheriting project.

`child` POM references `parent`

```
<parent>
  <groupId>com.company.app</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>
</parent>
```

Need to build the projects at the `child` level because build the `parent` project won't build `child` projects

Build the `parent` project, `child` projects tests won't run

## Create projects

> parent

- create maven project with pom file only, `<packaging>pom</packaging>`

> child

- in pom, specified parent element as below

```
<parent>
  <groupId>com.company.app</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>
</parent>
```

## Build the `child` project

mvn clean package

## How it works

> child pom

- specified a parent element in child pom file, add coordinates of the parent `groupId`, `artifactId` and `version`

- did not specify the `groupId` and `version` coordinates, and did not specify any properties and dependencies

> parent pom

- parent project is pom type

- specified properties and dependencies

> maven run on child project

- it inherits groupId, version, properties and dependencies defined in the parent

- location: by default, maven looks for the parent pom in the parent folder of child

- otherwise, it attempts to download the parent pom from the repository
