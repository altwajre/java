# Remote Repositories

- `$ mvn help:effective-pom` - view effective pom

- search for `repositories` on the terminal to see the central repository

```
    <repositories>
      <repository>
        <snapshots>
          <enabled>false</enabled>
        </snapshots>
        <id>central</id>
        <name>Central Repository</name>
        <url>https://repo.maven.apache.org/maven2</url>
      </repository>
    </repositories>
```

- `/usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml` - default maven settings.xml
