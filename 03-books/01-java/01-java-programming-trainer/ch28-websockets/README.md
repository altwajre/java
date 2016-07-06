# WebStocket

## Server

> `Run`, `Run As`, `Run on Server`

### no param

```
@ServerEndpoint("/hello")
public class HelloWebSocket {
```

> open client.html

browser output:

```
Hello from WebSocket Server
```

### has param

```
@ServerEndpoint("/hellohasparam/{userName}")
public class HelloWebSocketHasParam {
```

> open clienthasparam.html

browser output:

```
Hello Tom from WebSocket Server
```

### decoder

```
@ServerEndpoint(value = "/stockprice",
encoders = {StockEncoder.class},
decoders = {StockDecoder.class})
public class StockWebsocketEndpoint {
```

> open decoder.html

browser output:

```
IBM  Get Price
IBM: 86.65580566924814
```

### publish

```
@ServerEndpoint("/clock")
public class WebSocketClock {
	static ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
	private static Set<Session> allSessions;
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@OnOpen
	public void showTime(Session session){
		allSessions = session.getOpenSessions();
		
		// start the scheduler on the very first connection
		if(allSessions.size() == 1){
			// every second publish the time to all connected clients.
			timer.scheduleAtFixedRate(() -> sendTimeToAll(session), 0, 1, TimeUnit.SECONDS );
		}
	}
```

> open publish.html

browser output:

```
Local time: 01:21:13
```


