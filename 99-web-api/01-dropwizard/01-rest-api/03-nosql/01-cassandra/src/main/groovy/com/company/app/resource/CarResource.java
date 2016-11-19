package com.company.app.resource;

import com.company.app.DataSourceConfig;
import com.company.app.dao.CarDao;
import com.company.app.dao.CarDaoImpl;
import com.company.app.model.Car;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {
    private final CarDao carDao;

    @Inject
    public CarResource(DataSourceConfig config){
        this.carDao = new CarDaoImpl(config);
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
}
