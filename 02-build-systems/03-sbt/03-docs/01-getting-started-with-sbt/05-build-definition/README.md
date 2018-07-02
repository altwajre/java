# Build definition

https://www.scala-sbt.org/1.0/docs/Basic-Def.html

- Define Task `hello`

build.sbt

```
lazy val hello = taskKey[Unit]("An example task")

lazy val root = (project in file("."))
  .settings(
    hello := { println("Hello!") }
  )
```

- Run Task

```
$ sbt clean hello
```
