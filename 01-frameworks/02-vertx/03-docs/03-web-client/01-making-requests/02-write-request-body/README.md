# Writing request bodies - server client

http://vertx.io/docs/vertx-web-client/java/#_writing_request_bodies

## Server

> Launch

Right click `main`, click `run`

> output

Server listening at 8080
uri: /sendBuffer
Headers:
content-type: null
vert.x-eventloop-thread-1: chunk=Hello from Client
vert.x-eventloop-thread-1: request.endHandler() is called
uri: /sendStream
Headers:
content-type: null
vert.x-eventloop-thread-1: chunk={
  "customer":
  {
    "name": "Tom",
    "age": "28",
    "address": {
      "street": "1 main st",
      "city": "Seattle",
      "state": "WA"
    }
  },
  "car":
  {
    "model": "BMW",
    "color": "Black"
  }
}

vert.x-eventloop-thread-1: request.endHandler() is called
uri: /sendJsonObject
Headers:
content-type: application/json
vert.x-eventloop-thread-1: chunk={"name":"Tom","age":"18"}
vert.x-eventloop-thread-1: request.endHandler() is called
uri: /sendJson
Headers:
content-type: application/json
vert.x-eventloop-thread-1: chunk={"name":"Dick","age":28}
vert.x-eventloop-thread-1: request.endHandler() is called
uri: /sendForm
Headers:
content-type: application/x-www-form-urlencoded
vert.x-eventloop-thread-1: chunk=name=Harry&age=38
vert.x-eventloop-thread-1: request.endHandler() is called

## Client

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-1: StatusCode=200
vert.x-eventloop-thread-0: StatusCode=200
vert.x-eventloop-thread-1: StatusMessage=OK
vert.x-eventloop-thread-0: Body=Server-Chunk-0Server-Chunk-1Server-Chunk-2Server-Chunk-3Server-Chunk-4
vert.x-eventloop-thread-1: Body=Server-Chunk-0Server-Chunk-1Server-Chunk-2Server-Chunk-3Server-Chunk-4
vert.x-eventloop-thread-2: StatusCode=200
vert.x-eventloop-thread-2: Body=Server-Chunk-0Server-Chunk-1Server-Chunk-2Server-Chunk-3Server-Chunk-4
vert.x-eventloop-thread-0: StatusCode=200
vert.x-eventloop-thread-0: Body=Server-Chunk-0Server-Chunk-1Server-Chunk-2Server-Chunk-3Server-Chunk-4
vert.x-eventloop-thread-1: StatusCode=200
vert.x-eventloop-thread-1: Body=Server-Chunk-0Server-Chunk-1Server-Chunk-2Server-Chunk-3Server-Chunk-4
