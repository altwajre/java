# web client simple

## Server

> Launch

Right click `main`, click `run`

> output

```
Server listening at 8080
method: GET
uri: /clientget/b/c/page.html?param1=abc&param2=xyz&param3=123&param4=456
path: /clientget/b/c/page.html
query: param1=abc&param2=xyz&param3=123&param4=456
Headers: io.vertx.core.http.impl.HeadersAdaptor@702fcf91
host: localhost:8080
params:
param1: abc
param2: xyz
param3: 123
param4: 456

remoteAddress:127.0.0.1:55102
absoluteURI: http://localhost:8080/clientget/b/c/page.html?param1=abc&param2=xyz&param3=123&param4=456
vert.x-eventloop-thread-1: request.bodyHandler(): 
method: HEAD
uri: /clienthead/b/c/page.html?param1=abc&param2=xyz
path: /clienthead/b/c/page.html
query: param1=abc&param2=xyz
Headers: io.vertx.core.http.impl.HeadersAdaptor@4648644c
host: localhost:8080
params:
param1: abc
param2: xyz

remoteAddress:127.0.0.1:55101
absoluteURI: http://localhost:8080/clienthead/b/c/page.html?param1=abc&param2=xyz
vert.x-eventloop-thread-1: request.bodyHandler(): 
```

## Client

> Launch

Right click `main`, click `run`

> output

```
vert.x-eventloop-thread-1: StatusCode=200
vert.x-eventloop-thread-0: StatusCode=200
```

## WebClient.wrap HttpClient

> Launch

Right click `main`, click `run`

> output

vert.x-eventloop-thread-0: StatusCode=200
