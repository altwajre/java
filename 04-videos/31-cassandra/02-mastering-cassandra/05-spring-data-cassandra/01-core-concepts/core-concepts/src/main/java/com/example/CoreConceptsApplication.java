package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

@SpringBootApplication
public class CoreConceptsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(CoreConceptsApplication.class, args);
        
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("cwt");
        Select selectStatement = QueryBuilder.select().column("username").column("email")
                .column("fullname").column("password")
                .from("member"); // database (keyspace): home_security  table: home
        ResultSet resultSet = session.execute(selectStatement);
        for(Row row : resultSet){
            System.out.println(row.getString("fullname").toString());
        }
        session.close();
        cluster.close();

        ctx.close();
	}
}
