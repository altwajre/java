# 02-add-feature

## 01-no-features-found/README.md

## download cucumber jars

> download following jars from the public Maven repository at http://search.maven.org/

```
cucumber-core
cucumber-java
cucumber-jvm-deps
gherkin
```

## Run Test

> `java -cp "jars/*" cucumber.api.cli.Main -p pretty .` `-cp` is `classpath`, `entry-point` is `cucumber.api.cli.Main`

```
Output: since we haven't written any feature files yet, so no features found
No features found at [.]
0 Scenarios
0 Steps
0m0.000s
```

## Shell script

### Create shell script

> create `cucumber` at project root, and add `java -cp "jars/*" cucumber.api.cli.Main -p pretty .` to it

> make cucumber file executable `chmod u+x cucumber`

### Run shell script

> `./cucumber` at project root

