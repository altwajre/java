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

### Server

#### Intellij

> Launch App with Program args: server config.dev.yml

#### Command line

> `$ gradle clean run -PappArgs="['server','config.dev.yml']"`

### Client - cURL

#### Contact

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

#### Car

##### Dao

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

## Stored Procedure

### SQL

```
DROP PROCEDURE IF EXISTS get_car_by_make;

DELIMITER //
CREATE PROCEDURE get_car_by_make(IN make VARCHAR(40))
BEGIN
  SELECT * FROM webapi.car WHERE car.make = make;
END //
DELIMITER ;

CALL get_car_by_make('bmw');
```

### Code

**Repository**

```
public class CarRepository {
    DataSourceConfig config;

    public CarRepository(DataSourceConfig config){
        this.config = config;
    }

    public List<Car> getCars(String make) {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DataSource.createConnection(this.config);
             CallableStatement callableStatement = conn.prepareCall("call get_car_by_make(?)")
        ) {
            callableStatement.setString(1, make);
            callableStatement.executeQuery();

            try(ResultSet resultSet = callableStatement.getResultSet()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String _make = resultSet.getString(2);
                    Car car = new Car();
                    car.setId(id);
                    car.setMake(_make);
                    cars.add(car);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

}
```

**Dropwizard resource**

```
public class CarsResource {
...
    @GET
    @Path("/make/{make}")
    public Response getCarByMake(@PathParam("make") String make){
        List<Car> cars = carRepository.getCars(make);
        return Response.ok(cars).build();
    }
...
}
```

### Client

> `$ curl http://localhost:8080/cars/make/bmw`
