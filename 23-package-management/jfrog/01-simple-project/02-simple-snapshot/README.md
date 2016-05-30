# simple-project

> `settings.xml`

```
  <profiles>
    <profile>
      <repositories>
        <repository>
          <snapshots />
          <id>snapshots</id>
          <name>libs-snapshot</name>
          <url>http://localhost:8081/artifactory/libs-snapshot</url>
```

> `pom.xml`

```
  <distributionManagement>
    <repository>
      <id>snapshots</id>
      <name>LMDV-WHAN-snapshots</name>
      <url>http://localhost:8081/artifactory/libs-snapshot-local</url>
```

> `http://localhost:8081/artifactory/webapp/#/artifacts/browse/tree/General/libs-snapshot-local`

> click `Set Me Up`

> click `Generate Maven Settings`, download settings.xml and copy to the project root folder

> on the `Setup Me Up` popup, copy and paste `distributionManagement` to the `pom.xml`

> `mvn deploy -s settings.xml`

> go to http://localhost:8081/artifactory/webapp/#/artifacts/browse/tree/General/libs-snapshot-local to see deployed package
