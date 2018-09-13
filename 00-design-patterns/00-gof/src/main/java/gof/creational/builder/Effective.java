package gof.creational.builder;

import lombok.Builder;

/*
Consider a builder when faced with many constructor parameters

https://www.safaribooksonline.com/library/view/effective-java-2nd/9780137150021/ch02.html#ch02lev1sec2

Builder pattern
Instead of making the desired object directly, the client calls a constructor (or static factory) with all of the
required parameters and gets a builder object. Then the client calls setter-like methods on the builder and gets a
builder object to set each optional parameter of interest. Finally, the client calls a parameterless build method to
generate the object, which is immutable.
 */
class Customer {
  private String name;
  private int age;

  public static Builder builder() {
    return new Builder();
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public static class Builder {
    private String name;
    private int age;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder age(int age) {
      this.age = age;
      return this;
    }

    public Customer build() {
      return new Customer(this);
    }
  }

  public Customer(Builder builder) {
    this.name = builder.name;
    this.age = builder.age;
  }
}

@Builder
class User {
  private String name;
  private int age;
}

public class Effective {
  public static void main(String[] args) {
    testCustomBuilder();

    testLombokBuilder();
  }

  private static void testCustomBuilder() {
    System.out.println("#testCustomBuilder");
    final Customer tom = Customer
        .builder()
        .name("Tom")
        .age(18)
        .build();
    System.out.println("name=" + tom.getName() + ", age=" + tom.getAge());
  }

  private static void testLombokBuilder() {
    System.out.println("#testLombokBuilder");
    final User dick = User
        .builder()
        .name("Dick")
        .age(28)
        .build();
    System.out.println(dick);
  }
}
/*
#testCustomBuilder
name=Tom, age=18
#testLombokBuilder
User(name=Dick, age=28)
 */
