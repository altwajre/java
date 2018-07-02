# Dependency Scope

debug option: `mvn -X`

- `$ mvn -X compile` - junit jar is not included

- `$ mvn -X test` - junit jar is included

```
[DEBUG] Classpath: [/Users/whan/Documents/workspace/app/target/test-classes
 /Users/whan/Documents/workspace/app/target/classes
 /Users/whan/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar]
```
