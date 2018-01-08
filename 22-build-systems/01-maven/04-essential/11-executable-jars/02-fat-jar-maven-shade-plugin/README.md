# Create a fat Jar file – Maven Shade Plugin

https://www.mkyong.com/maven/create-a-fat-jar-file-maven-shade-plugin/

> Package it

$ mvn clean package

> List `jar` content

```
$ jar tf target/fat-jar-maven-shade-plugin-1.0-SNAPSHOT.jar
META-INF/MANIFEST.MF
META-INF/
com/
com/company/
com/company/app/
com/company/app/App.class
META-INF/maven/
META-INF/maven/com.company.app/
META-INF/maven/com.company.app/fat-jar-maven-shade-plugin/
META-INF/maven/com.company.app/fat-jar-maven-shade-plugin/pom.xml
META-INF/maven/com.company.app/fat-jar-maven-shade-plugin/pom.properties
META-INF/LICENSE.txt
META-INF/NOTICE.txt
org/
org/joda/
org/joda/time/
org/joda/time/base/
org/joda/time/base/AbstractDateTime.class
org/joda/time/base/AbstractDuration.class
...
org/joda/time/Weeks.class
org/joda/time/YearMonth$Property.class
org/joda/time/YearMonth.class
org/joda/time/YearMonthDay$Property.class
org/joda/time/YearMonthDay.class
org/joda/time/Years.class
META-INF/maven/joda-time/
META-INF/maven/joda-time/joda-time/
META-INF/maven/joda-time/joda-time/pom.xml
META-INF/maven/joda-time/joda-time/pom.properties
```

> Run

```
$ java -jar target/fat-jar-maven-shade-plugin-1.0-SNAPSHOT.jar
2017-06-25
```
