package com.company.server.resources;

import com.codahale.metrics.annotation.Timed;
import com.company.server.core.Person;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/get-person")
@Produces(MediaType.APPLICATION_JSON)
public class ServerResource {
    private final String name;

    public ServerResource(String name){
        this.name = name;
    }

    @GET
    @Timed
    public Person getPerson(@QueryParam("name") Optional<String> name)
    {
        return new Person(this.name, 18);
    }
}
