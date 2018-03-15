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
    static int getCount = 0;
    static int postCount = 0;
    static int putCount = 0;
    static int delCount = 0;
    private final ContactDao contactDao;

    public ContactResource() {
        contactDao = new ContactDaoImpl();
    }

    @GET
    public Response getContacts() {
        if (getCount++ < 5) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        List<Contact> contacts = contactDao.getContacts();
        return Response.ok(contacts).build();
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        Contact contact = contactDao.getContact(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(Contact contact) throws URISyntaxException {
        if (postCount++ < 5) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        int newContactId = contactDao.createContact(contact);
        return Response.created(new URI("contacts/" + String.valueOf(newContactId))).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact) {
        if (putCount++ < 5) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        contactDao.updateContact(contact);
        return Response.ok(contact).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id) {
        if (delCount++ < 5) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        Contact toDeletedContact = contactDao.getContact(id);
        contactDao.deleteContact(toDeletedContact);
        return Response.noContent().build();
    }

}
