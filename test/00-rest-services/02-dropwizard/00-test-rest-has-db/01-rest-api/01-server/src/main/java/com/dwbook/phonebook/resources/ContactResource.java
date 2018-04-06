package com.dwbook.phonebook.resources;

import com.dwbook.phonebook.dao.ContactDAO;
import com.dwbook.phonebook.representations.Contact;
import org.skife.jdbi.v2.DBI;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    private final ContactDAO contactDao;

    public ContactResource(DBI jdbi) {
        contactDao = jdbi.onDemand(ContactDAO.class);
    }

    @GET
    @Path("/all")
    public Response getContacts() {
        // retrieve information about the contact with the provided id
        Iterator<Contact> contacts = contactDao.getContacts();
        return Response.ok(contacts).build();
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        // retrieve information about the contact with the provided id
        Contact contact = contactDao.getContactById(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(@Valid Contact contact) throws URISyntaxException {
        // OK, no validation errors
        // Store the new contact
        int newContactId = contactDao.createContact(contact.getFirstName(),
                contact.getLastName(), contact.getPhone());
        return Response.created(new URI("contact/" + String.valueOf(newContactId))).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(
            @PathParam("id") int id,
            @Valid Contact contact) {
        contactDao.updateContact(id, contact.getFirstName(),
                contact.getLastName(), contact.getPhone());
        return Response.ok(
                new Contact(id, contact.getFirstName(), contact.getLastName(),
                        contact.getPhone())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id) {
        // delete the contact with the provided id
        // ...
        contactDao.deleteContact(id);
        return Response.noContent().build();
    }
}
