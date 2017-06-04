# Getting Started

- Installing Maven on Mac, Linux

## Settings - .m2 folder

`/Users/username/.m2`

```
$ ls
repository	settings.xml
```

> Local

This is the repository in your computer filesystem

> Remote

This is the repository from where the required Maven files get downloaded

> Mirrors

These are repository managers, such as Nexus and Artifactory, that mirror various repositories

## Standard directory layout

> build a Java project and list `target` folder

```
﻿app$ mvn clean package

app﻿$ ls target
app-1.0-SNAPSHOT.jar  generated-sources       maven-archiver  surefire-reports
classes               generated-test-sources  maven-status    test-classes
```

> `classes` folder

Compiled source files are placed in this folder. This folder will also contain resources,
such as XML and property files are part of the source, placed in src/main/resources

> `test-classes` folder

Compiled test source files are available in this folder.

> `surefire-reports` folder

Test reports are separately placed in this folder. Typically, both XML and HTML report formats are available.

> `.jar` file

The generated project artifact is present in this folder.

> `maven-archiver` and `maven-status`

Hold information used by Maven during the build.
