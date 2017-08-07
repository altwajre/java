# profiles

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

> set environment variable

ISSUE: set env as below does NOT work.

```
`export TEST_ENV=prod`
```

> build - production

```
$ mvn package
```

jar file is deployed to `06-profiles/artifact/production`
