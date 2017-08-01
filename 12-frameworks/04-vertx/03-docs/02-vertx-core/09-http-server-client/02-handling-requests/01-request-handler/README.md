# request handler server client

The handler() is called every time a chunk of the request body arrives

When request handler is called, the request object does not have the entire request body at this point.
Because the body may be very large (e.g. a file upload) and we don't generally want to buffer the entire body
in memory before handing it to you, as that could cause the server to exhaust available memory.

```
Buffer totalBuffer = Buffer.buffer();

request.handler(buffer -> {
  System.out.println("I have received a chunk of the body of length " + buffer.length());
  totalBuffer.appendBuffer(buffer);
});

request.endHandler(v -> {
  System.out.println("Full body received, length = " + totalBuffer.length());
});
```

## Server

> Launch

Right click `main`, click `run`

> output

Server listening at 8080
uri: /getNow/b/c/page.html?param1=abc&param2=xyz
path: /getNow/b/c/page.html
query: param1=abc&param2=xyz
Headers: {Host: localhost:8080}
host: localhost:8080
params:
param1: abc
param2: xyz

remoteAddress:127.0.0.1:51212
absoluteURI: http://localhost:8080/getNow/b/c/page.html?param1=abc&param2=xyz
vert.x-eventloop-thread-1: req.endHandler() is called
uri: /post
path: /post
query: null
Headers: {Host: localhost:8080}
host: localhost:8080
params:

remoteAddress:127.0.0.1:51214
absoluteURI: http://localhost:8080/post
Hello fr
vert.x-eventloop-thread-1: req.endHandler() is called
uri: /requestPost
path: /requestPost
query: null
Headers: {Host: localhost:8080}
host: localhost:8080
params:

remoteAddress:127.0.0.1:51213
absoluteURI: http://localhost:8080/requestPost
Tom
Benny
vert.x-eventloop-thread-1: req.endHandler() is called

## Client

> Launch

Right click `main`, click `run`

> output

Tom
Benny
statusCode: 200
StatusCode: 200
Response body: Hello from server - GET request
Response body: Hello from server - POST request
statusCode: 200
Response body: Hello from server - POST request
