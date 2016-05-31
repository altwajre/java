package com.company.app.resources;

import com.company.app.Topic;

import javax.ws.rs.*;
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
