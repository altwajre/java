# java -jar

## modify build.gradle

- add following

```
jar {
    manifest {
        attributes(
                'Class-Path': configurations.compile.collect { it.getName() }.join(' '),
                'Main-Class': 'com.company.app.App'
        )
    }
}
```

## run

**no arg**

- `$ java -jar build/libs/java-jar-1.0-SNAPSHOT.jar`

```
# Hello
```

**pass args**

- `$ java -jar build/libs/java-jar-1.0-SNAPSHOT.jar a b`

```
# Hello
a
b
```
