package com.company.app.dao;

import com.company.app.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getCars();
    Car getCar(int id);
    int createCar(Car car);
    void updateCar(Car car);
    void deleteCar(Car car);
}
