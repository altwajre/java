# handling responses - server client

http://vertx.io/docs/vertx-web-client/java/#_handling_http_responses

## Server

> Launch

Right click `main`, click `run`

> output

Server listening at 8080
uri: /default
uri: /json
uri: /jsonObject

## Client

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-2: StatusCode=200
vert.x-eventloop-thread-2: Body={"name":"default","age":"38"}
vert.x-eventloop-thread-1: StatusCode=200
vert.x-eventloop-thread-1: Body=User(name=json, age=28)
vert.x-eventloop-thread-0: StatusCode=200
vert.x-eventloop-thread-0: Body={"name":"jsonObject","age":"18"}