package com.company.app;

import java.util.Optional;

class Insurance {
    public Insurance(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}

class Car {
    public Car(String name, Optional<Insurance> insurance) {
        this.name = name;
        this.insurance = insurance;
    }

    private String name;
    private Optional<Insurance> insurance;

    public String getName() {
        return name;
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Person {
    public Person(String name, Optional<Car> car) {
        this.name = name;
        this.car = car;
    }

    private String name;
    private Optional<Car> car;

    public String getName() {
        return name;
    }

    public Optional<Car> getCar() {
        return car;
    }
}

public class App
{
    public static void main( String[] args )
    {
        flatMapOptional();
        mapOptionalLimitation();
    }
    private static void flatMapOptional() {
        System.out.println("# flatMapOptional");
        Car car1 = new Car("car-1", Optional.of(new Insurance("get insurance-name by using flatMapOptional")));

        Person person1 = new Person("Tom", Optional.of(car1));

        Optional<Person> optPerson = Optional.of(person1);

        Optional<Car> optCar = optPerson.flatMap(p -> p.getCar());

        Optional<Insurance> optInsurance = optCar.flatMap(c -> c.getInsurance());

        String insuranceName = optInsurance
                .map(i -> i.getName())
                .orElse("Unknown");

        System.out.println(insuranceName);
    }

    private static void mapOptionalLimitation() {
        System.out.println("# mapOptionalLimitation");
        Car car1 = new Car("car-1", Optional.of(new Insurance("get insurance-name by using map")));
        Person person1 = new Person("Tom", Optional.of(car1));

        Optional<Person> optPerson = Optional.of(person1);

        Optional<String> optional = optPerson.map(p -> p.getName());

        Optional<Optional<Car>> optOptCar = optPerson.map(p -> p.getCar());

        Optional<Optional<Insurance>> optOptInsurance = optOptCar.get().map(c -> c.getInsurance());

        Optional<String> insuranceName = optOptInsurance.get().map(i -> i.getName());

        System.out.println(insuranceName.get());
    }
}
/*
output:
# flatMapOptional
get insurance-name by using flatMapOptional
# mapOptionalLimitation
get insurance-name by using map
 */
