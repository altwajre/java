# scale multiple servers

http://vertx.io/docs/vertx-core/java/#_scaling_sharing_tcp_servers

The handlers of any TCP server are always executed on the same event loop thread.

This means that if you are running on a server with a lot of cores, and you only have this one instance deployed then
you will have at most one core utilised on your server.

In order to utilise more cores of your server you will need to deploy more instances of the server.

When you deploy another server on the same host and port as an existing server it doesn’t actually try and create a new
server listening on the same host/port.

Instead it internally maintains just a single server, and, as incoming connections arrive it distributes them in a
round-robin fashion to any of the connect handlers.

Consequently Vert.x TCP servers can scale over available cores while each instance remains single threaded.

## Server

> Launch

Right click `main`, click `run`

> output

*One server: i = 1*

```
vert.x-eventloop-thread-1: Server is listening on 1234
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-4
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-6
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-2
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-1
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-0
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-5
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-3
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-7
```


*3 servers: i = 3 - round-robin fashion*

When you deploy another server on the same host and port as an existing server it doesn’t actually try and create a new
server listening on the same host/port.
Instead it internally maintains just a single server, and, as incoming connections arrive it distributes them in a
round-robin fashion to any of the connect handlers.

```
vert.x-eventloop-thread-2: Server is listening on 1234
vert.x-eventloop-thread-1: Server is listening on 1234
vert.x-eventloop-thread-3: Server is listening on 1234
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-3: server.connectHandler()
vert.x-eventloop-thread-2: server.connectHandler()
vert.x-eventloop-thread-1: server.connectHandler()
vert.x-eventloop-thread-3: server.connectHandler()
vert.x-eventloop-thread-2: server.connectHandler()
vert.x-eventloop-thread-3: server.connectHandler()
vert.x-eventloop-thread-2: server.connectHandler()
vert.x-eventloop-thread-3: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-6
vert.x-eventloop-thread-2: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-4
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-2
vert.x-eventloop-thread-3: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-3
vert.x-eventloop-thread-2: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-7
vert.x-eventloop-thread-1: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-1
vert.x-eventloop-thread-3: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-5
vert.x-eventloop-thread-2: socket.handler() Hello from Client_Name=vert.x-eventloop-thread-0
```

## Client

> Launch

Right click `main`, click `run`

> output

```
vert.x-eventloop-thread-7: Connected
vert.x-eventloop-thread-0: Connected
vert.x-eventloop-thread-4: Connected
vert.x-eventloop-thread-5: Connected
vert.x-eventloop-thread-6: Connected
vert.x-eventloop-thread-3: Connected
vert.x-eventloop-thread-1: Connected
vert.x-eventloop-thread-2: Connected
```
