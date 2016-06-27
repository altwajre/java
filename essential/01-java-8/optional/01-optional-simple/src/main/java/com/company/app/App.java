package com.company.app;

import java.util.Optional;

/*
Optional map conceptually similar to the stream’s map method. The map operation applies the provided function to
each element of a stream. You could also think of an Optional object as a particular collection of data, containing at
most a single element. If the Optional contains a value, then the function passed as argument to map transforms that
value. If the Optional is empty, then nothing happens.
 */
class Car {
    public Car(String name) {
        this.name = name;
    }
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
class Person {
    public Person(String name, Optional<Car> optCar) {
        this.name = name;
        this.optCar = optCar;
    }
    private String name;
    private Optional<Car> optCar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Car> getOptCar() {
        return optCar;
    }

    public void setOptCar(Optional<Car> optCar) {
        this.optCar = optCar;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Car car = new Car("car-1");
        Person person = new Person("Tom", Optional.of(car));
        Optional<Person> optPerson = Optional.of(person);

        Optional<String> optName = optPerson.map(p -> p.getName());
        System.out.println(optName.get());

        Optional<Optional<Car>> optCar = optPerson.map(p -> p.getOptCar());
        System.out.println(optCar.get().get().getName());

        Optional<String> carName = optPerson
                .flatMap(p -> p.getOptCar())
                .map(c -> c.getName());
        System.out.println(carName.get());
    }
}
/*
output:
Tom
car-1
car-1
 */
