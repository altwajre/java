package com.company;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hello")
public class HelloWebSocket {
	@OnOpen
	public void greetTheClient(Session session){
		
		System.out.println("greetTheClient() is invoked ");
		try {
			session.getBasicRemote().sendText("Hello from WebSocket Server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
