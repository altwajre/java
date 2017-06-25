package com.packt.legacy.powermockito;

public class PrivateMethod {

	private String secretValue(){
		return "#$$%^&*";
	}
	
	public String exposeTheSecretValue(){
		return secretValue();
	}
}
