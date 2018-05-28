package com.dwbook.phonebook.resources;

import com.dwbook.phonebook.dao.ContactDAO;
import com.dwbook.phonebook.representations.Contact;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    private final ContactDAO contactDao;
    public ContactResource(DBI jdbi){
        contactDao = jdbi.onDemand(ContactDAO.class);
    }

    @GET
    @Path("/all")
    public Response getContacts(){
        Iterator<Contact> contacts = contactDao.getContacts();
        return Response.ok(contacts).build();
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id){
        // retrieve information about the contact with the provided id
        Contact contact = contactDao.getContactById(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(Contact contact) throws URISyntaxException {
        // store the new contact
        // ...
        int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id){
        // delete the contact with the provided id
        // ...
        contactDao.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(
            @PathParam("id") int id,
            Contact contact){
        // update the contact with the provided ID
        // ...
        contactDao.updateContact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.ok(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone()))
                .build();
    }
}
