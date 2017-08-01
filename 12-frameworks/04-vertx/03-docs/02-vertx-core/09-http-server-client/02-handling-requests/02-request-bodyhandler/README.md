# request bodyhandler server client

The body handler is called once when all the body has been received

```
request.bodyHandler(totalBuffer -> {
  System.out.println("Full body received, length = " + totalBuffer.length());
});
```

## Server

> Launch

Right click `main`, click `run`

> output

Server listening at 8080
uri: /requestPost/b/c/page.html?param1=abc&param2=xyz
path: /requestPost/b/c/page.html
query: param1=abc&param2=xyz
Headers: {Host: localhost:8080}
host: localhost:8080
params:
param1: abc
param2: xyz

remoteAddress:127.0.0.1:54488
absoluteURI: http://localhost:8080/requestPost/b/c/page.html?param1=abc&param2=xyz
request.bodyHandler(): TomBenny

## Client

> Launch

Right click `main`, click `run`

> output

Tom
Benny
statusCode: 200
Response body: Hello from server - POST
