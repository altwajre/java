package com.company.app;

import java.util.Optional;

class Insurance{
    private String name;
    public String getName(){
        return name;
    }
}
class Car{
    private Optional<Insurance> insurance = Optional.empty();
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
public class App
{
    public static void main( String[] args )
    {
        isPresentTest();

        orElseTest();
    }

    private static void orElseTest() {
        System.out.println("# orElseTest");
        Car car = new Car();
        Optional<Insurance> insurance = car.getInsurance();
        System.out.println(insurance.map(i -> i.getName()).orElse("Unknown"));
    }

    private static void isPresentTest() {
        System.out.println("# isPresentTest");
        Car car = new Car();
        Optional<Insurance> insurance = car.getInsurance();
        if(insurance.isPresent()){
            System.out.println("insurance is-present");
        }
        else{
            System.out.println("insurance is not present");
        }
    }
}
/*
output:
# isPresentTest
insurance is not present
# orElseTest
Unknown
 */
