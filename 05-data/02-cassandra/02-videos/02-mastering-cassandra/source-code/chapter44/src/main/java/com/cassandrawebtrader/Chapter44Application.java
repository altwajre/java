package com.cassandrawebtrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Chapter44Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Chapter44Application.class, args);
        
        Server server = ctx.getBean("server", Server.class);
        server.printMembers();
        
        ctx.close();
    }
}
