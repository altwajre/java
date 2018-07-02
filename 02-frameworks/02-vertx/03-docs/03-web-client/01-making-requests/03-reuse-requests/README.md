# Reusing Requests - server client

http://vertx.io/docs/vertx-web-client/java/#_reusing_requests

## Server

> Launch

Right click `main`, click `run`

> output

Server listening at 8080
uri: /some-uri
uri: /some-uri

## Client

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-0: StatusCode=200
vert.x-eventloop-thread-1: StatusCode=200
