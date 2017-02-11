package com.company.app.resource;

import com.company.app.dao.ContactDao;
import com.company.app.dao.ContactDaoImpl;
import com.company.app.model.Contact;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
    static int count = 0;
    private final ContactDao contactDao;

    public ContactResource(){
        contactDao = new ContactDaoImpl();
    }

    @GET
    public Response getContacts(){

        if(count++ < 5){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

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
        Contact toDeletedContact = contactDao.getContact(id);
        contactDao.deleteContact(toDeletedContact);
        return Response.ok(toDeletedContact).build();
    }

}
