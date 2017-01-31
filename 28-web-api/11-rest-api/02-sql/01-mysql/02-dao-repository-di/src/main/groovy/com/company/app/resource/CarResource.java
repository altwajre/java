package com.company.app.resource;

import com.company.app.DataSourceConfig;
import com.company.app.dao.CarDao;
import com.company.app.dao.CarDaoImpl;
import com.company.app.model.Car;
import com.company.app.repository.CarRepository;
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

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {
    private final CarDao carDao;
    private final CarRepository carRepository;

    @Inject
    public CarResource(DataSourceConfig config){
        this.carDao = new CarDaoImpl(config);
        this.carRepository = new CarRepository(config);
    }

    @GET
    public Response getCars(){
        List<Car> cars = carDao.getCars();
        return Response.ok(cars).build();
    }

    @GET
    @Path("/{id}")
    public Response getCarById(@PathParam("id") int id){
        Car car = carDao.getCar(id);
        return Response.ok(car).build();
    }

    @GET
    @Path("/make/{make}")
    public Response getCarByMake(@PathParam("make") String make){
        List<Car> cars = carRepository.getCars(make);
        return Response.ok(cars).build();
    }

    @POST
    public Response createCar(Car car) throws URISyntaxException {
        int newCarId = carDao.createCar(car);
        return Response.created(new URI(String.valueOf(newCarId))).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCar(@PathParam("id") int id, Car car){
        carDao.updateCar(car);
        return Response.ok(car).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") int id){
        Car car = carDao.getCar(id);
        carDao.deleteCar(car);
        return Response.ok(car).build();
    }

}
