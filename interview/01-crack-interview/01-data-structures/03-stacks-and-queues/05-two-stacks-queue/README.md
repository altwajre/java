# two-stacks-queue

maven-archetype-quickstart project

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
$ java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App
--Print stackNewest disks--
3
2
1
--Print stackOldest disks--
Empty
#remove 1
--Print stackNewest disks--
Empty
--Print stackOldest disks--
2
3
#add 11
--Print stackNewest disks--
11
--Print stackOldest disks--
2
3
#remove 2
--Print stackNewest disks--
11
--Print stackOldest disks--
3
#remove 3
--Print stackNewest disks--
11
--Print stackOldest disks--
Empty
#remove 11
--Print stackNewest disks--
Empty
--Print stackOldest disks--
Empty
```
