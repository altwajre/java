# Timeouts - server client

http://vertx.io/docs/vertx-web-client/java/#_timeouts

## Server

> Launch

Right click `main`, click `run`

> output

Server listening at 8080
uri: /some-uri

## Client

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-0: Error=java.util.concurrent.TimeoutException: The timeout period of 800ms has been exceeded while executing GET /some-uri for host localhost
