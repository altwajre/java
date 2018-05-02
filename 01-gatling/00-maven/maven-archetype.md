# Maven Archetype

https://gatling.io/docs/2.3/extensions/maven_archetype/

- generate gatling maven project

```
$ mvn archetype:generate
enter gatling
enter 1 to select archetype
1: 2.0.0-RC1
2: 2.0.0-RC2
3: 2.0.0-RC3
4: 2.0.0-RC4
5: 2.0.0-RC5
6: 2.0.0-RC6
7: 2.0.0
8: 2.1.0
9: 2.1.1
10: 2.1.2
11: 2.1.5
12: 2.1.6
13: 2.1.7
14: 2.2.0-M2
15: 2.2.0
16: 2.2.1
17: 2.2.4
18: 2.2.5
19: 2.3.0
20: 2.3.1
enter 13 to select gatling archetype <- works <gatling-plugin.version>2.1.7</gatling-plugin.version>
```

## Ensure testing following run

> intellij

right click `Engine` and click `Run Engine`

> maven command line

mvn clean gatling:execute
