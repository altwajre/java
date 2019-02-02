package gof.structural.composite.java;

import java.util.ArrayList;
import java.util.List;

/*
https://learning.oreilly.com/videos/learn-design-patterns/9781788838795/9781788838795-video4_5

 */
class Employee {
  private String name;
  private Integer id;

  // Composition of same object type
  private List<Employee> subordinates = new ArrayList<>();

  public Employee(String name, Integer id) {
    this.name = name;
    this.id = id;
  }

  public void addSubordinate(Employee employee) {
    subordinates.add(employee);
  }

  public void removeSubordinate(Employee employee) {
    subordinates.remove(employee);
  }

  @Override
  public String toString() {
    String level = subordinates.size() == 0 ? "Non Manager" : "Manager";
    String node = subordinates.size() == 0 ? "No subordinates" : subordinates.toString();

    return "\n" + level + " {name='" + name + '\'' + ", subordinates=" + node + "}";
  }
}
public class Simple {
  public static void main(String[] args) {
    Employee cto = new Employee("Tom", 1);

    Employee level_1_1 = new Employee("Kinesh",2);
    Employee level_1_2 = new Employee("Karteek",3);
    Employee level_1_3 = new Employee("Ash",4);

    Employee level_2_1 = new Employee("Amy",5);
    Employee level_2_2 = new Employee("Courty",6);
    Employee level_2_3 = new Employee("Jackson",7);
    Employee level_2_4 = new Employee("Leticia",8);

    Employee level_3_1 = new Employee("Peter",9);
    Employee level_3_2 = new Employee("Jodi",10);
    Employee level_3_3 = new Employee("William",11);
    Employee level_3_4 = new Employee("Darly",12);

    cto.addSubordinate(level_1_1);
    cto.addSubordinate(level_1_2);
    cto.addSubordinate(level_1_3);

    level_1_1.addSubordinate(level_2_1);
    level_1_1.addSubordinate(level_2_2);

    level_1_2.addSubordinate(level_2_3);
    level_1_2.addSubordinate(level_2_4);

    level_2_2.addSubordinate(level_3_1);
    level_2_2.addSubordinate(level_3_2);

    level_2_3.addSubordinate(level_3_3);
    level_2_3.addSubordinate(level_3_4);

    // Manager: Courty
    System.out.println("\nManager:");
    System.out.println(level_2_2);

    // IC: Peter
    System.out.println("\nIndividual Contributor:");
    System.out.println(level_3_1);

    // CTO
    System.out.println("\nCTO:");
    System.out.println(cto);
  }
}
/*
Manager:

Manager {name='Courty', subordinates=[
Non Manager {name='Peter', subordinates=No subordinates},
Non Manager {name='Jodi', subordinates=No subordinates}]}

Individual Contributor:

Non Manager {name='Peter', subordinates=No subordinates}

CTO:

Manager {name='Tom', subordinates=[
Manager {name='Kinesh', subordinates=[
Non Manager {name='Amy', subordinates=No subordinates},
Manager {name='Courty', subordinates=[
Non Manager {name='Peter', subordinates=No subordinates},
Non Manager {name='Jodi', subordinates=No subordinates}]}]},
Manager {name='Karteek', subordinates=[
Manager {name='Jackson', subordinates=[
Non Manager {name='William', subordinates=No subordinates},
Non Manager {name='Darly', subordinates=No subordinates}]},
Non Manager {name='Leticia', subordinates=No subordinates}]},
Non Manager {name='Ash', subordinates=No subordinates}]}
 */
