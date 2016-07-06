package com.company;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hellohasparam/{userName}")
public class HelloWebSocketHasParam {
	@OnOpen
	public void greetTheClient(Session session, @PathParam("userName") String userName){
		
		System.out.println("greetTheClient() has param is invoked " + userName);
		try {
			session.getBasicRemote().sendText("Hello " + userName + " from WebSocket Server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
