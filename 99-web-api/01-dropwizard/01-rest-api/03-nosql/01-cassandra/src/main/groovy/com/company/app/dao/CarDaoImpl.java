package com.company.app.dao;

import com.company.app.DataSourceConfig;
import com.company.app.model.Car;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

    DataSourceConfig config;

    public CarDaoImpl(DataSourceConfig config) {
        this.config = config;
    }

    @Override
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        try(Session session = DataSource.createSession(this.config)){
            String queryString = "SELECT id, make, color FROM webapi.car";

            ResultSet resultSet = session.execute(queryString);

            resultSet.forEach(r -> {
                int id = r.getInt("id");
                String make = r.getString("make");
                String color = r.getString("color");
                Car car = new Car(id, make, color);
                cars.add(car);
            });
        }
        return cars;
    }

    @Override
    public Car getCar(int id) {
        Car car = null;
        try(Session session = DataSource.createSession(this.config)){
            String queryString = "SELECT id, make, color FROM webapi.car WHERE id = " + id;

            ResultSet resultSet = session.execute(queryString);

            Row one = resultSet.one();
            int _id = one.getInt("id");
            String make = one.getString("make");
            String color = one.getString("color");
            car = new Car(_id, make, color);
        }
        return car;
    }

    @Override
    public int createCar(Car car) {
        return 0;
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCar(Car car) {

    }
}
