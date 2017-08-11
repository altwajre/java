# import pom project

## pom.xml

> top level pom.xml

packaging is pom - `<packaging>pom</packaging>`

```
  <groupId>com.company.app</groupId>
  <artifactId>import-pom-project</artifactId>
  <version>1.0-SNAPSHOT</version>
  <modules>
    <module>pom</module>
    <module>product</module>
    <module>offer</module>
  </modules>
  <packaging>pom</packaging>

  <name>import-pom-project</name>
```

> pom project pom.xml

delete `pom/src`

Specify `<properties>` and `<dependencyManagement>` (optional <scope>test</scope>)

```
  <properties>
    <junit.version>4.12</junit.version>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```

> child (product and offer) project pom.xml

import dependency from pom project above as below, and specify dependencies with `groupId` and `artifactId`

```
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>com.company.app</groupId>
        <artifactId>pom</artifactId>
        <version>1.0-SNAPSHOT</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
    </dependency>
  </dependencies>
```

> Test Run

`mvn test`

```
Running com.company.app.AppTest
# testProduct
product_1
product_2
product_3
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.049 sec

Running com.company.app.AppTest
# testOffer
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.04 sec
```

> plugins

Since plugins management import does not work, 
the child project will import plugin themselves if the plugin is not already in super pom
