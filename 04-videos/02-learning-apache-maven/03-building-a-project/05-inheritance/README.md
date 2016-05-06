# Inheritance

> install parent pom - put parent pom to local repository, so it is available for child pom

```
LMDV-WHAN:parent whan$ mvn install
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Learning Maven Examples 1.0.0
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-install-plugin:2.4:install (default-install) @ parent ---
[INFO] Installing /Users/whan/Documents/workspace/parent/pom.xml to /Users/whan/.m2/repository/com/company/app/parent/1.0.0/parent-1.0.0.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.370 s
[INFO] Finished at: 2016-05-05T22:30:51-07:00
[INFO] Final Memory: 8M/309M
[INFO] ------------------------------------------------------------------------
```

> display child pom information

```
LMDV-WHAN:child whan$ mvn help:effective-pom
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>com.company.app</groupId>
    <artifactId>parent</artifactId>
    <version>1.0.0</version>
  </parent>
  <groupId>com.company.app</groupId>
  <artifactId>child</artifactId>
  <version>1.0.0</version>
```