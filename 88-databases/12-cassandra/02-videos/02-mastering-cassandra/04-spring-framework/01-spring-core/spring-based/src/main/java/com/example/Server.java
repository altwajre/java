package com.example;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class Server {
//	private Client client = new Client1();
	
	@Resource // dependency injection - inject Client1 by spring
	private Client client;
	public void callClient(){
		client.saySomething();
	}
}
