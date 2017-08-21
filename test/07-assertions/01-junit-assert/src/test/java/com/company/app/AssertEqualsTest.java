package com.company.app;

import lombok.Builder;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

@Data
@Builder
class User {
  private String name;
  private int age;
}

class UserWithoutOverrideEquals {
  private String name;
  private int age;
  public UserWithoutOverrideEquals(String name, int age) {
    this.name = name;
    this.age = age;
  }
//  @Override
//  public boolean equals(Object obj) {
//    if(obj == this) return true;
//    if(!(obj instanceof UserWithoutOverrideEquals)) return false;
//    UserWithoutOverrideEquals p = (UserWithoutOverrideEquals)obj;
//    return p.name == this.name && p.age == this.age;
//  }
//  @Override
//  public int hashCode(){
//    int result = 17;
//    result = 31 * result + age;
//    return result;
//  }
}

public class AssertEqualsTest {
  @Test
  public void testObjectOverrideEquals() {
    final User tom1 = User.builder().name("Tom").age(18).build();
    final User tom2 = User.builder().name("Tom").age(18).build();
    Assert.assertEquals(tom1, tom2);
  }
  @Test
  public void testObjectWithoutOverrideEquals() {
    final UserWithoutOverrideEquals tom1 = new UserWithoutOverrideEquals("Tom", 18);
    final UserWithoutOverrideEquals tom2 = new UserWithoutOverrideEquals("Tom", 18);
    Assert.assertNotEquals(tom1, tom2);
  }
}
