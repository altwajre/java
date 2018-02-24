package com.company.app;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/*
https://bitbucket.org/asomov/snakeyaml/wiki/Documentation

resources/spec/car-class.yaml
plate: 12-XP-F4
wheels:
- {id: 1}
- {id: 2}
- {id: 3}
- {id: 4}
- {id: 5}

 */
public class App
{
    public static void main( String[] args )
    {
        Constructor constructor = new Constructor(Car.class);
        TypeDescription carDescription = new TypeDescription(Car.class);
        carDescription.putListPropertyType("wheels", Wheel.class);
        constructor.addTypeDescription(carDescription);

        Yaml yaml = new Yaml(constructor);
        Car car = (Car) yaml.load(Util.getLocalResource("spec/car-class.yaml"));
        System.out.println(car.getPlate());

        car.getWheels().forEach(w -> System.out.println(w.getId()));
    }
}
/*
output:
12-XP-F4
1
2
3
4
5
 */
