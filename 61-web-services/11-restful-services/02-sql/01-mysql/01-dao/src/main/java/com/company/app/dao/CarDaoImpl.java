package com.company.app.dao;

import com.company.app.DataSourceConfig;
import com.company.app.models.Car;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

    DataSourceConfig config;

    public CarDaoImpl(DataSourceConfig config){
        this.config = config;
    }

    @Override
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement select = conn.prepareStatement("SELECT id, make FROM webapi.car")
        ) {
            select.executeQuery();

            try(ResultSet resultSet = select.getResultSet()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String make = resultSet.getString(2);
                    Car car = new Car();
                    car.setId(id);
                    car.setMake(make);
                    cars.add(car);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Car getCar(int id) {
        Car car = null;
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement select = conn.prepareStatement("SELECT id, make FROM webapi.car WHERE id = ?")
        ) {
            select.setInt(1, id);
            select.executeQuery();

            try(ResultSet resultSet = select.getResultSet()) {
                if (resultSet.next()) {
                    car = new Car();
                    car.setId(resultSet.getInt(1));
                    car.setMake(resultSet.getString(2));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    @Override
    public int createCar(Car car) {
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement insert = conn.prepareStatement(
                     "INSERT INTO webapi.car (make) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            insert.setString(1, car.getMake());
            int affectedRows = insert.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Create failed, no rows affected");
            }

            try(ResultSet generatedKeys = insert.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt(1);
                }
                else{
                    throw new SQLException("Creating failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateCar(Car car) {
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement update = conn.prepareStatement("UPDATE webapi.car SET make = ? WHERE id = ?")
        ) {
            update.setString(1, car.getMake());
            update.setInt(2, car.getId());

            int affectedRows = update.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Create failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCar(Car car) {
        try (Connection conn = DataSource.createConnection(this.config);
             PreparedStatement delete = conn.prepareStatement("DELETE FROM webapi.car WHERE id = ?")
        ) {
            delete.setInt(1, car.getId());

            int affectedRows = delete.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Create failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
