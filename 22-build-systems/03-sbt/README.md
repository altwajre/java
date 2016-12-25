# SBT - Simple Build Tool

## Installation

```
$ brew install sbt
```

## Modify SBT

```
$ sudo nano `which sbt`
```

## sandbox

```
$ nano build.sbt
name := "sandbox"

scalaVersion := "2.12.1"
$ sbt
[info] Set current project to sandbox (in build file:/Users/whan/sbt/sandbox/)
> console
[info] Starting scala interpreter...
[info] 
Welcome to Scala 2.12.1 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_45).
Type in expressions for evaluation. Or try :help.

scala> :q

[success] Total time: 11 s, completed Dec 24, 2016 8:50:54 PM
> exit
```
