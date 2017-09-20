# javers

https://javers.org/

Object auditing and diff framework for Java

https://javers.org/documentation/

https://javers.org/documentation/getting-started/

> Create a JaVers instance

Javers javers = JaversBuilder.javers().build();

> Object diff

```
final Person tomOld = new Person("Tom", 18);
final Person tomNew = new Person("Tommy", 28);
final Diff diff = javers.compare(tomNew, tomOld);
```

> Object audit


