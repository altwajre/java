# profiles

http://maven.apache.org/guides/introduction/introduction-to-profiles.html

https://www.safaribooksonline.com/library/view/learning-apache-maven/9781771373661/video212501.html

> youtube

https://www.youtube.com/watch?v=j3gEgh44iQQ

## basic

> pom.xml

```
  <build>
    <directory>artifact/development</directory>
  </build>

  <profiles>
    <profile>
      <id>production</id>
      <build>
        <directory>artifact/production</directory>
      </build>
    </profile>
  </profiles>
```

> build - default development env

```
$ mvn package
```

jar file is deployed to `06-profiles/artifact/development`

> build with production

```
$ mvn -Pproduction package
```

jar file is deployed to `06-profiles/artifact/production`

## maven profiles activation

> pom.xml

```
  <build>
    <directory>artifact/development</directory>
  </build>

  <profiles>
    <profile>
      <id>production</id>
      <activation>
        <property>
          <name>TEST_ENV</name>
          <value>prod</value>
        </property>
      </activation>
      <build>
        <directory>artifact/production</directory>
      </build>
    </profile>
  </profiles>
```

> build with activation `-DTEST_ENV=prod` - production

```
$ mvn -DTEST_ENV=prod package
```

jar file is deployed to `06-profiles/artifact/production`
