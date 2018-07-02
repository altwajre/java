# gatling maven

## Create maven project

```
$ mvn archetype:generate
enter gatling
enter 1 to select archetype
enter 20 to select gatling archetype
```

> gatling.conf

- mute

```
mute = true                 # When set to true, don't ask for simulation name nor run description (currently only used by Gatling SBT plugin)
```

- jenkins

```
keepAlive = false                               # Allow pooling HTTP connections (keep-alive header automatically added)
connectTimeout = 80000                          # Timeout when establishing a connection
handshakeTimeout = 80000                        # Timeout when performing TLS hashshake
```

## Run Test

- Run All

```
mvn clean gatling:test
```

- Run One Test

```
mvn clean gatling:test -Dgatling.simulationClass=com.company.app.BasicSimulation
```

> intellij

Right click `Engine`, select test to `Run Engine`
