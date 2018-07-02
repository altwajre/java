# Single project dependency management

## pom.xml

> add `dependency` to `<dependencyManagement>`

```
    <dependencyManagement>
      <dependencies>
        <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <version>1.16.6</version>
        </dependency>
      </dependencies>
    </dependencyManagement>
```

> specify `dependency` in `<dependencies>`

```
  <dependencies>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
    </dependency>
  </dependencies>
```
