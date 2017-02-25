package com.dwbook.phonebook.resources;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import com.dwbook.phonebook.representations.Contact;

@Produces(MediaType.TEXT_PLAIN)
@Path("/client")
public class ClientResource {
    private Client client;
    public ClientResource(Client client){
        this.client = client;
    }

    @GET
    @Path("showContact")
    public String showContact(@QueryParam("id") int id) {
        Contact c = client.target("http://localhost:8080/contact/" + id).request().get(Contact.class);
        String output = "ID: " + id
                + "\nFirst name: " + c.getFirstName()
                + "\nLast name: " + c.getLastName()
                + "\nPhone: " + c.getPhone();
        return output;
    }

    @GET
    @Path("newContact")
    public Response newContact(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("phone") String phone) {
        Response response = client
                .target("http://localhost:8080/contact")
                .request()
                .post(Entity.entity(new Contact(0, firstName, lastName, phone), MediaType.APPLICATION_JSON));
        return response;
    }

    @GET
    @Path("updateContact")
    public Response updateContact(
            @QueryParam("id") int id,
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("phone") String phone) {
        Response response = client
                .target("http://localhost:8080/contact/" + id)
                .request()
                .put(Entity.entity(new Contact(id, firstName, lastName, phone), MediaType.APPLICATION_JSON));
        return response;
    }

    @GET
    @Path("deleteContact")
    public Response deleteContact(@QueryParam("id") int id) {
        Response response = client.target("http://localhost:8080/contact/" + id).request().delete();
        return response;
    }
}
