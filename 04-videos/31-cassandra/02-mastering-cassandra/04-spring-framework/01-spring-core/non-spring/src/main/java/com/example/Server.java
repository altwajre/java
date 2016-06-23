package com.example;

public class Server {
	private Client client = new Client1();
	public void callClient(){
		client.saySomething();
	}
}
