# deploy service

## Run Service in Background

> Run in background

https://www.mkyong.com/java/how-do-run-a-java-program-in-backgroud-unix-linux/

nohup java -jar commerce-test-service-1.0-SNAPSHOT.jar &

> Run as background service <- not working

http://www.baeldung.com/spring-boot-app-as-a-service

/etc/init.d
sudo ln -s commerce-test-service-1.0-SNAPSHOT.jar /etc/init.d/commerce-test-service-1.0-SNAPSHOT
sudo service commerce-test-service-1.0-SNAPSHOT start <- not working
sudo /etc/init.d/commerce-test-service-1.0-SNAPSHOT start <- not working

> Kill the java process

ps aux | grep java
whan       383  0.0  0.0 112660   968 pts/1    S+   17:08   0:00 grep --color=auto java
whan     32658 27.6  3.2 8262636 522400 pts/0  Sl+  17:06   0:25 java -jar commerce-test-service-1.0-SNAPSHOT.jar

kill -9 32658
