# handling exceptions server client

## Server

> Launch

Right click `main`, click `run`

Click `stop` debug after launch Client

> output

Server listening at 8080

## Client

> Launch

Right click `main`, click `run`

> output

Received exception: Connection was closed
io.vertx.core.VertxException: Connection was closed
	at io.vertx.core.http.impl.ClientConnection.handleClosed(ClientConnection.java:408)
	at io.vertx.core.net.impl.VertxHandler$$Lambda$20/312416475.run(Unknown Source)
