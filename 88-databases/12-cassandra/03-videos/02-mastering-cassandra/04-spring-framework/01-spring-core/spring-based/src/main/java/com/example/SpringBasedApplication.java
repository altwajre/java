package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBasedApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBasedApplication.class, args);
		
//		Server server = new Server();

		Server server = ctx.getBean("server", Server.class);
		server.callClient();
		
		ctx.close();
	}
}
