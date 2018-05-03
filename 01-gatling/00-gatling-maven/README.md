# gatling maven

## Create maven project

```
$ mvn archetype:generate
enter gatling
enter 1 to select archetype
enter 20 to select gatling archetype
```

> mute

gatling.conf

```
mute = true
```

## Run Test

> maven

```
mvn clean gatling:test
```

> intellij

Right click `Engine`, select test to `Run Engine`
