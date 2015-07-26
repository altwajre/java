# selenium simple

https://code.google.com/p/selenium/wiki/GettingStarted

### Running the Selenium Server

download the latest selenium-server-standalone from http://selenium-release.storage.googleapis.com/index.html

By the time it is written (5/5/2015), I did following

download the latest selenium-server-standalone-2.45.0.jar

Running Selenium Manually on the where you download the selenium-server-standalone-2.45.0.jar

> `java -jar selenium-server-standalone-2.45.0.jar`

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/app-1.0-SNAPSHOT.jar config.prod.yml`
