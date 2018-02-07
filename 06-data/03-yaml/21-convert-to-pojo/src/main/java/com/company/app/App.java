package com.company.app;

import lombok.ToString;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@ToString
class Order {
    public Integer id;
    public String description;
}
@ToString
class Customer{
    public String name;
    public Integer age;
    public List<Order> orders;
}
public class App
{
    public static void main( String[] args ) throws Exception {

        ConvertToPojo();

    }

    /*
    yaml:
    --- !<tag:customer>
    name: Tom
    age: 18
    orders:
      - id: 123
        description: order_1
      - id: 789
        description: order_2
     */
    private static void ConvertToPojo() throws FileNotFoundException {
        String pathname = "src/main/resources/spec/customer.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml(new Constructor(Customer.class));
        Customer tom = (Customer) yaml.load(inputStream);
        System.out.println(tom);
    }
    /*
    output:
    Customer(name=Tom, age=18, orders=[Order(id=123, description=order_1), Order(id=789, description=order_2)])
     */

}
