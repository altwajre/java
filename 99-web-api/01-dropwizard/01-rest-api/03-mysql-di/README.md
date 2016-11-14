# Web Api in-memory

## Guice Dependency Injection

> Create a module to provide DataSourceConfig

```
public class EcommModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    public DataSourceConfig provideConfig(EcommConfiguration ecommConfiguration) {
        return ecommConfiguration.getDataSourceConfig();
    }
}
```

> Add App.initialize() to bootstrap dependency injection

```
    @Override
    public void initialize(Bootstrap<EcommConfiguration> bootstrap) {
        GuiceBundle<EcommConfiguration> guiceBundle = GuiceBundle.<EcommConfiguration>newBuilder()
                .addModule(new EcommModule())
                .setConfigClass(EcommConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }
```

> Add @Inject to resource constructor ContactsResource.ContactsResource()

```
    @Inject
    public ContactsResource(DataSourceConfig config){
        this.contactDao = new ContactDaoImpl(config);
    }

```

## MySql

> Start MySql Server: `sudo /usr/local/mysql/bin/mysqld -u root`

> Stop MySql Server: `sudo /usr/local/mysql/support-files/mysql.server stop`

## SQL Code

ContactDaoImpl.java

## Run the App

### Intellij

#### Server

Launch App with Program args: server

#### Client - cURL

##### Contact

**GET All**
> `$ curl http://localhost:8080/contacts`

```
[{"id":1,"name":"Tom"},{"id":2,"name":"Dick"},{"id":3,"name":"Harry"}]
```

**GET**

> `$ curl http://localhost:8080/contacts/2`

```
{"id":2,"name":"Dick"}
```

**POST**

> `$ curl -X POST -H "Content-Type: application/json" -d '{"id": 4, "name": "Will"}' "http://localhost:8080/contacts"`

**PUT**

> `curl -X PUT -H "Content-Type: application/json" -d '{"id": 1, "name": "Will"}' "http://localhost:8080/contacts/1"`

```
{"id":1,"name":"Will"}
```

**DELETE**

> `curl -X DELETE "http://localhost:8080/contacts/4"`

##### Car

**GET All**
> `$ curl http://localhost:8080/cars`

```
[{"id":1,"make":"Honda"},{"id":2,"make":"BMW"},{"id":3,"make":"Ford"}]
```

**GET**

> `$ curl http://localhost:8080/cars/2`

```
{"id":2,"make":"BMW"}
```

**POST**

> `$ curl -X POST -H "Content-Type: application/json" -d '{"id": 4, "make": "Super"}' "http://localhost:8080/cars"`

**PUT**

> `curl -X PUT -H "Content-Type: application/json" -d '{"id": 1, "make": "Super"}' "http://localhost:8080/cars/1"`

```
{"id":1,"name":"Will"}
```

**DELETE**

> `curl -X DELETE "http://localhost:8080/cars/4"`
