# simple server client

## Server

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-1: Server is listening on 1234
vert.x-eventloop-thread-1: {
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

vert.x-eventloop-thread-1: Hello from Client!

## Client

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-1: Connected: send file
vert.x-eventloop-thread-0: Connected: send string
127.0.0.1:1234
