FROM java:8
WORKDIR /deployments
ADD  /target/tools-debug-swap.jar /deployments/app.jar
CMD java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n -jar app.jar