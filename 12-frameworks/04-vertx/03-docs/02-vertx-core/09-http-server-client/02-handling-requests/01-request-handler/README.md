# simple server client

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
TomBenny
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
