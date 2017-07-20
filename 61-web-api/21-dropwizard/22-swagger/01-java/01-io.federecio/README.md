# Swagger

## Code

1, add swagger dependency to build.gradle

```
    compile 'io.federecio:dropwizard-swagger:0.7.0'
```

2, add swagger api annotations to resource

```
@Path("/contacts")
@Api("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactsResource {
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
```

3, add SwaggerBundle to App.initialize() bootstrap

```
public class App extends Application<Configuration> {
    @Override
    public void initialize(Bootstrap<Configuration> bootstrap){
        bootstrap.addBundle(new SwaggerBundle<Configuration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Configuration configuration) {
                SwaggerBundleConfiguration config = new SwaggerBundleConfiguration();
                config.setResourcePackage("com.company.app.resource");
                return config;
            }
        });
    }
```

## Run the App

### Intellij

#### Server

Launch App with Program args: server

#### Go to swagger spec

http://localhost:8080/swagger
