# Understanding the Maven lifecycle, phases, and goals

> Build Maven project

- Run Maven command `mvn package`

- Observe the various steps that get executed

> Built-in build lifecycles

- `default` lifecycle handles project build and deployment

- `clean` lifecycle cleans up the files and folders produced by Maven

- `site` lifecycle handles the creation of project documentation

`package` phase indicates `default` lifecycle

> Table of phases

| Phase             | Plugin                        | Goal     |
|-------------------|-------------------------------|----------|
| clean             | Maven Clean plugin            | clean    |
| site              | Maven Site plugin             | site     |
| process-resources | Maven Resources plugin        | resource |
| compile           | Maven Compiler plugin         | compile  |
| test              | Maven Surefire plugin         | test     |
| package           | Varies based on the packaging | jar      |
| install           | Maven Install plugin          | install  |
| deploy            | Maven Deploy plugin           | deploy   |


*Examples*

```
﻿mvn process-resources
```
