package com.company.app.resource;

import com.company.app.DataSourceConfig;
import com.company.app.dao.ContactDao;
import com.company.app.dao.ContactDaoImpl;
import com.company.app.model.Contact;
import com.google.inject.Inject;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
    private final ContactDao contactDao;

    @Inject
    public ContactResource(DataSourceConfig config){
        this.contactDao = new ContactDaoImpl(config);
    }

    @GET
    public Response getContacts(){
        List<Contact> contacts = contactDao.getContacts();
        return Response.ok(contacts).build();
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id){
        Contact contact = contactDao.getContact(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(Contact contact) throws URISyntaxException {
        int newContactId = contactDao.createContact(contact);
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact){
        contactDao.updateContact(contact);
        return Response.ok(contact).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id){
        Contact contact = contactDao.getContact(id);
        contactDao.deleteContact(contact);
        return Response.ok(contact).build();
    }

}
