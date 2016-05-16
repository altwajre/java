package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NonSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NonSpringApplication.class, args);
		
		Server server = new Server();
		server.callClient();
	}
}
