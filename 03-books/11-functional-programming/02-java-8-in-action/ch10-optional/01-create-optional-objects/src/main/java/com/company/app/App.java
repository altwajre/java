package com.company.app;

import java.util.Optional;

class Insurance{
    private String name;
    public String getName(){
        return name;
    }
}
class Car{
    public Car(String name) {
        this.name = name;
    }
    private String name;
    public String getName() {
        return name;
    }
    private Optional<Insurance> insurance = Optional.empty();
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
    @Override
    public String toString() {
        return this.name;
    }
}

public class App
{
    public static void main( String[] args )
    {
        emptyOptional();

        optionalFromNonNullValue();

        optionalFromNull();
    }

    private static void optionalFromNull() {
        System.out.println("# optionalFromNull");
        Car car = new Car("car-2");
        Optional<Car> optCar = Optional.ofNullable(car);
        System.out.println(optCar);
        System.out.println(optCar.get());

        Car nullCar = null;
        Optional<Car> optNullCar = Optional.ofNullable(nullCar);
        System.out.println(optNullCar);
    }

    private static void optionalFromNonNullValue() {
        System.out.println("# optionalFromNonNullValue");
        Car car = new Car("car-1");
        Optional<Car> optCar = Optional.of(car);
        System.out.println(optCar);
        System.out.println(optCar.get());

        // exception occur below
//        Car nullCar = null;
//        Optional<Car> optNullCar = Optional.of(nullCar);
    }

    private static void emptyOptional() {
        System.out.println("# emptyOptional");
        Optional<Car> optCar = Optional.empty();
        System.out.println(optCar);
    }
}
