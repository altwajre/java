package com.company.app;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ReferenceChange;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.annotation.TypeName;
import org.junit.Test;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@Data
@TypeName("Employee")
class Employee {

  @Id
  private String name;

  private String position;

  private int salary;

  private Employee boss;

  private List<Employee> subordinates = new ArrayList<>();

  private Address primaryAddress;
  private Address postalAddress;

  private int age;

  public Employee(String name) {
    this(name, 10000);
  }

  public Employee(String name, int salary) {
    checkNotNull(name);
    this.name = name;
    this.salary = salary;
  }

  public Employee(String name, int salary, String position) {
    checkNotNull(name);
    checkNotNull(position);
    this.name = name;
    this.salary = salary;
    this.position = position;
  }

  public Employee addSubordinate(Employee employee) {
    checkNotNull(employee);
    employee.boss = this;
    subordinates.add(employee);
    return this;
  }

  public Employee addSubordinates(Employee... employees) {
    checkNotNull(employees);
    for (Employee e : employees) {
      addSubordinate(e);
    }
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Employee)) {
      return false;
    }
    Employee that = (Employee) obj;

    return this.name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("name", name)
        .append("salary", salary)
        .append("boss", boss != null ? boss.name : "")
        .append("subordinates", subordinates.size())
        .toString();
  }
}

public class CompareGraphs {
  @Test
  public void shouldDetectSalaryChange() {
    // given
    final Javers javers = JaversBuilder.javers().build();

    final Employee oldBoss = new Employee("Big Boss")
        .addSubordinates(
            new Employee("Noisy Manager"),
            new Employee("Great Developer", 10000));

    final Employee newBoss = new Employee("Big Boss")
        .addSubordinates(
            new Employee("Noisy Manager"),
            new Employee("Great Developer", 20000));

    // when
    final Diff diff = javers.compare(oldBoss, newBoss);

    // then
    final ValueChange change = diff.getChangesByType(ValueChange.class).get(0);

    assertThat(change.getAffectedLocalId()).isEqualTo("Great Developer");
    assertThat(change.getPropertyName()).isEqualTo("salary");
    assertThat(change.getLeft()).isEqualTo(10000);
    assertThat(change.getRight()).isEqualTo(20000);
    System.out.println(diff);
  }
/*
Diff:
1. ValueChange{globalId:'com.company.app.Employee/Great Developer', property:'salary', oldVal:'10000', newVal:'20000'}
 */

  @Test
  public void shouldDetectHired() {
    //given
    Javers javers = JaversBuilder.javers().build();

    Employee oldBoss = new Employee("Big Boss")
        .addSubordinates(
            new Employee("Great Developer"));

    Employee newBoss = new Employee("Big Boss")
        .addSubordinates(
            new Employee("Great Developer"),
            new Employee("Hired One"),
            new Employee("Hired Second"));

    //when
    Diff diff = javers.compare(oldBoss, newBoss);

    //then
    assertThat(diff.getObjectsByChangeType(NewObject.class))
        .hasSize(2)
        .containsOnly(new Employee("Hired One"),
            new Employee("Hired Second"));

    System.out.println(diff);
  }
/*
Diff:
1. NewObject{globalId:'Employee/Hired Second'}
2. NewObject{globalId:'Employee/Hired One'}
3. ListChange{globalId:'Employee/Big Boss', property:'subordinates', containerChanges:[(1).added:'Employee/Hired One', (2).added:'Employee/Hired Second']}
 */

  @Test
  public void shouldDetectBossChange() {
    // given
    final Javers javers = JaversBuilder.javers().build();

    final Employee oldBoss = new Employee("Big Boss")
        .addSubordinates(
            new Employee("Manager One")
                .addSubordinates(new Employee("Great Developer")),
            new Employee("Manager Second"));

    final Employee newBoss = new Employee("Big Boss")
        .addSubordinates(
            new Employee("Manager One"),
            new Employee("Manager Second")
                .addSubordinates(new Employee("Great Developer")));

    // when
    final Diff diff = javers.compare(oldBoss, newBoss);

    // then
    final ReferenceChange change = diff.getChangesByType(ReferenceChange.class).get(0);

    assertThat(change.getAffectedLocalId()).isEqualTo("Great Developer");
    assertThat(change.getLeft().value()).endsWith("Manager One");
    assertThat(change.getRight().value()).endsWith("Manager Second");

    System.out.println(diff);
  }
/*
Employee/Manager One
Diff:
1. ListChange{globalId:'Employee/Manager One', property:'subordinates', containerChanges:[(0).removed:'Employee/Great Developer']}
2. ReferenceChange{globalId:'Employee/Great Developer', property:'boss', oldRef:'Employee/Manager One', newRef:'Employee/Manager Second'}
3. ListChange{globalId:'Employee/Manager Second', property:'subordinates', containerChanges:[(0).added:'Employee/Great Developer']}
 */

  @Test
  public void shouldDetectFiredInLargeDepthStructure() {
    // given
    final Javers javers = JaversBuilder.javers().build();

    final Employee oldBoss = new Employee("Big Boss");
    Employee boss = oldBoss;
    for(int i = 0; i < 1000; i++) {
      boss.addSubordinate(new Employee("Emp no." + i));
      boss = boss.getSubordinates().get(0);
    }

    final Employee newBoss = new Employee("Big Boss");

    // when
    final Diff diff = javers.compare(oldBoss, newBoss);

    // then
    assertThat(diff.getChangesByType(ObjectRemoved.class)).hasSize(1000);
  }

}
