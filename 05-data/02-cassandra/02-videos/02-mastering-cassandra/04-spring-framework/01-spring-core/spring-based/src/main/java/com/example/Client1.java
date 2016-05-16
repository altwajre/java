package com.example;

import org.springframework.stereotype.Component;

@Component // concrete component of Client to be injected by spring, it tells spring to create an instance in the application context
public class Client1 implements Client {
	@Override
	public void saySomething(){
		System.out.println("This is Client1");
	}
}
