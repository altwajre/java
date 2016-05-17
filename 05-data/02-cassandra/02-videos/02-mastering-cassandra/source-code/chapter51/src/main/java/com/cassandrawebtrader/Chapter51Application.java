package com.cassandrawebtrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@SpringBootApplication
public class Chapter51Application {

	private static Cluster cluster;
	private static Session session;
	
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Chapter51Application.class, args);
        
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect("cwt");
        
        cluster.close();
        
        ctx.close();
    }
}
