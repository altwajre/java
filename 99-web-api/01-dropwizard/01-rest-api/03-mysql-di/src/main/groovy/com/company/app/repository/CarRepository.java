package com.company.app.repository;

import com.company.app.DataSourceConfig;
import com.company.app.dao.DataSource;
import com.company.app.model.Car;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
