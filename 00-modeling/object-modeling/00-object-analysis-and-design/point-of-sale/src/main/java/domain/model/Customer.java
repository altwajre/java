package domain.model;

public class Customer {
  private Integer id;
  private String name;
  private Integer age;
  public Customer(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer(60);
    buffer.append("Customer: ");
    buffer.append("\nName: " + this.name);
    buffer.append("\nAge: " + this.age);
    return buffer.toString();
  }

}
