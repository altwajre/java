# simple-project

> `settings.xml`

```
  <profiles>
    <profile>
      <repositories>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>central</id>
          <name>libs-release</name>
          <url>http://localhost:8081/artifactory/libs-release</url>
```

> `pom.xml`

```
  <distributionManagement>
    <repository>
      <id>central</id>
      <name>LMDV-WHAN-releases</name>
      <url>http://localhost:8081/artifactory/libs-release-local</url>
```

> `http://localhost:8081/artifactory/webapp/#/artifacts/browse/tree/General/libs-release-local`

> click `Set Me Up`

> click `Generate Maven Settings`, download settings.xml and copy to the project root folder

> on the `Setup Me Up` popup, copy and paste `distributionManagement` to the `pom.xml`

> `mvn deploy -s settings.xml`

> go to http://localhost:8081/artifactory/webapp/#/artifacts/browse/tree/General/libs-release-local to see deployed package
