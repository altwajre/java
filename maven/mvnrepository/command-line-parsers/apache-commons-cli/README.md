# apache-commons-cli

https://commons.apache.org/proper/commons-cli/usage.html

## Compile

> `mvn package`

## Run

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App`

```
Output:
out.print default behavior
```

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App -t`

```
Output:
out.print date and time
```

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App -p`

> or `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App -print`

```
Output:
org.apache.commons.cli.MissingArgumentException: Missing argument for option: p
usage: ant
 -help              print help message
 -p,--print <arg>   print message
 -t                 display current time
```

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App -p=hello`

```
Output:
hello
```

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App -p="hello world"`

```
Output:
hello world
```

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App -p=dev.config`

```
Output:
dev.config
```

> `java -cp target/test-1.0-SNAPSHOT.jar com.company.test.App -help`

```
Output:
usage: ant
 -a,--all   test all
 -help      print help message
 -t         display current time
 ```

## Run Application

### Intellij

> Run, Run 'App'

```
Output:
default behavior
```
