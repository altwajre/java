# remove-dups

maven-archetype-quickstart project

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
$ java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App
#deleteDupsTest
#createlinkedList
#printLinkedList
7->2->3->3->4->5->5->null
#deleteDups
#printLinkedList
7->2->3->4->5->null
#deleteDupsWithoutBufferTest
#createlinkedList
#printLinkedList
7->2->3->3->4->5->5->null
#printLinkedList
7->2->3->4->5->null
```
