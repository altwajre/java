package com.company.app.resource;

import com.company.app.dao.ContactDao;
import com.company.app.dao.ContactDaoImpl;
import com.company.app.exception.NotFoundException;
import com.company.app.model.Contact;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

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
@Api("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactsResource {
    private final ContactDao contactDao;

    public ContactsResource(){
        contactDao = new ContactDaoImpl();
    }

    @GET
    @ApiOperation(value = "Get Contacts")
    public Response getContacts(){
        List<Contact> contacts = contactDao.getContacts();
        return Response.ok(contacts).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Get contact by Id",
            notes = "Returns a contact when 0 < Id or nonintegers will simulate API error conditions",
            response = Contact.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid Id supplied"),
                    @ApiResponse(code = 404, message = "Contact not found")
            })
    public Response getContact(@PathParam("id") int id) throws NotFoundException {
        Contact contact = contactDao.getContact(id);
        if(contact == null) {
            throw new NotFoundException(404, "Contact not found");
        }
        return Response.ok(contact).build();
    }

    @POST
    @ApiOperation(value = "Add a new Contact")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input")})
    public Response createContact(Contact contact) throws URISyntaxException {
        int newContactId = contactDao.createContact(contact);
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation(value = "Update an existing contact by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid Id supplied"),
                    @ApiResponse(code = 404, message = "Contact not found"),
                    @ApiResponse(code = 405, message = "Validation exception")
            })
    public Response updateContact(@PathParam("id") int id, Contact contact){
        contactDao.updateContact(contact);
        return Response.ok(contact).build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete an existing contact by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid Id supplied"),
                    @ApiResponse(code = 404, message = "Contact not found"),
                    @ApiResponse(code = 405, message = "Validation exception")
            })
    public Response deleteContact(@PathParam("id") int id){
        Contact toDeletedContact = contactDao.getContact(id);
        contactDao.deleteContact(toDeletedContact);
        return Response.ok(toDeletedContact).build();
    }

}
