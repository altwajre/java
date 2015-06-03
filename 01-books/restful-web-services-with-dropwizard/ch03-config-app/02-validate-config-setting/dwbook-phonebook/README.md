# externalize config - dwbook-phonebook

maven-archetype-quickstart project

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

output when "messageRepetitions: 13" in config.yml

```
$ java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml
config.yml has an error:
  * messageRepetitions must be less than or equal to 10 (was 13)
```

> edit config.yml, change messageRepetitions less than 10 such as "messageRepetitions: 3"

output

```
$ java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml
INFO  [2015-06-03 21:55:19,557] org.eclipse.jetty.util.log: Logging initialized @834ms
$$ This is optional
INFO  [2015-06-03 21:55:19,610] com.dwbook.phonebook.App: Method App#run() called
This is a message defined in the configuration file config.yaml.
This is a message defined in the configuration file config.yaml.
This is a message defined in the configuration file config.yaml.
```
