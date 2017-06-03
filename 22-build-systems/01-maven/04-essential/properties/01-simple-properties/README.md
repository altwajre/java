# maven properties

> add `<revision>1-SNAPSHOT</revision>` to properties tag, and change version to use `${revision}`
```
  <version>2.${revision}</version>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <revision>1-SNAPSHOT</revision>
  </properties>
```

## Compile

> `mvn package`

## Run

> `java -jar target/app-2.1-SNAPSHOT.jar `

output:
```
Hello World!
```