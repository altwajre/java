# Create a fat Jar file – Maven Shade Plugin

https://www.mkyong.com/maven/create-a-fat-jar-file-maven-shade-plugin/

> Package it

```
$ mvn clean package
...
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ fat-jar-maven-shade-plugin ---
[INFO] Building jar: /Users/whan/Desktop/java/22-build-systems/01-maven/03-docs/02-mkyong/fat-jar-maven-shade-plugin/target/fat-jar-maven-shade-plugin-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-shade-plugin:2.4.3:shade (default) @ fat-jar-maven-shade-plugin ---
[INFO] Including joda-time:joda-time:jar:2.9.4 in the shaded jar.
[INFO] Replacing original artifact with shaded artifact.
[INFO] Replacing /Users/whan/Desktop/java/22-build-systems/01-maven/03-docs/02-mkyong/fat-jar-maven-shade-plugin/target/fat-jar-maven-shade-plugin-1.0-SNAPSHOT.jar with /Users/whan/Desktop/java/22-build-systems/01-maven/03-docs/02-mkyong/fat-jar-maven-shade-plugin/target/fat-jar-maven-shade-plugin-1.0-SNAPSHOT-shaded.jar
[INFO] Dependency-reduced POM written at: /Users/whan/Desktop/java/22-build-systems/01-maven/03-docs/02-mkyong/fat-jar-maven-shade-plugin/dependency-reduced-pom.xml
[INFO] Dependency-reduced POM written at: /Users/whan/Desktop/java/22-build-systems/01-maven/03-docs/02-mkyong/fat-jar-maven-shade-plugin/dependency-reduced-pom.xml
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.086 s
[INFO] Finished at: 2017-06-25T21:52:11-07:00
[INFO] Final Memory: 19M/210M
[INFO] ------------------------------------------------------------------------
```

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
