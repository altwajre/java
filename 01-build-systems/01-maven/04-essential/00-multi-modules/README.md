# Combining inheritance and aggregation - multi modules

https://www.safaribooksonline.com/library/view/apache-maven-cookbook/9781785286124/ch09s04.html

`Aggregation` is defined with a top-down approach
`parent` pom includes `child`

```
<modules>
  <module>child</module>
</modules>
```

`Inheritance` is defined the bottom-up approach
`child` POM references `parent`

```
<parent>
  <groupId>com.company.app</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>
</parent>
```

`child` tests will always run when build at `parent` or `child` level

> `project inheritance` - build at `child`

By using the project inheritance, we can share common build attributes such as properties and dependencies across all children.

The parent is not aware of the child.

> `project aggregation` - build at `parent`

We can also aggregate modules and build them together.

Each module is not aware of the aggregation.

## projects

> `parent`

- update the `pom` as below

```
  <groupId>com.company.app</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <modules>
    <module>child</module>
  </modules>
```

> `child`

- update the `pom` as below

```
    <parent>
        <artifactId>parent</artifactId>
        <groupId>com.company.app</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
```
