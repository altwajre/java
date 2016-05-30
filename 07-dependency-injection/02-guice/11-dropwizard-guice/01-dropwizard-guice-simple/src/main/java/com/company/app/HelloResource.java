package com.company.app;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloResource {
    private final String message;
    private final String name;

    @Inject
    public HelloResource(
            @Named("message") String message,
            @Named("name") String name) {
        System.out.println(message);
        System.out.println(name);
        this.message = message;
        this.name = name;
    }

    @GET
    public String hello(){
        return name + " says " + message;
    }
}
