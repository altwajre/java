package com.company.app.server.resources;

import com.company.app.server.Topic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    @GET
    @Path("/all")
    public Response getContacts() {
        Topic topic = new Topic("WPF");
        return Response.ok(topic).build();
    }
}
