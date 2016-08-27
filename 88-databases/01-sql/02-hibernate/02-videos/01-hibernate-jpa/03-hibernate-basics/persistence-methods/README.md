# persistence methods

pom.xml

```
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>4.3.6.Final</version>
    </dependency>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-annotations</artifactId>
      <version>3.5.6-Final</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.33</version>
    </dependency>
```

resources

```
hibernate.cfg.xml
hibernate.properties
```

## Workbench

verify the inserted record

```
use ifinances;
select * from FINANCES_USER;
```
