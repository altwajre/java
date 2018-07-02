# simple server client

## Server

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-2: Server is listening on 1234
vert.x-eventloop-thread-2: {
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

vert.x-eventloop-thread-2: Hello from Client!
The socket has been closed
The socket has been closed
Server is now closed

## Client

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-1: Connected: send file
vert.x-eventloop-thread-0: Connected: send string
127.0.0.1:1234
