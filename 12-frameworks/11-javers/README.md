# javers

https://javers.org/

Object auditing and diff framework for Java

https://javers.org/documentation/

https://javers.org/documentation/getting-started/

> Create a JaVers instance

Javers javers = JaversBuilder.javers().build();

> Object diff

```
Person tommyOld = new Person("tommy", "Tommy Smart");
Person tommyNew = new Person("tommy", "Tommy C. Smart");

Diff diff = javers.compare(tommyOld, tommyNew);
```

> Object audit

```
Person robert = new Person("bob", "Robert Martin");
javers.commit("user", robert);
```

