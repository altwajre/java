package com.company.app;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.changelog.SimpleTextChangeLog;
import org.javers.core.diff.Change;
import org.javers.core.metamodel.annotation.TypeName;
import org.javers.repository.jql.QueryBuilder;
import org.junit.Test;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Data
@TypeName("Employee")
class Employee {

  @Id
  private String name;

  private String position;

  private int salary;

  private Employee boss;

  private List<Employee> subordinates = new ArrayList<>();

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

public class ChangeLogExample {
  @Test
  public void shouldPrintTextChangeLog() {
    // given
    final Javers javers = JaversBuilder.javers().build();
    final Employee bob = new Employee("Bob", 9_000, "Scrum master");
    javers.commit("hr.manager", bob);

    // make some changes
    bob.setPosition("Team Lead");
    bob.setSalary(11_000);
    // commit first change
    javers.commit("hr.manager", bob);

    bob.addSubordinates(new Employee("Trainee One"), new Employee("Trainee Two"));
    // commit second change
    javers.commit("hr.manager", bob);

    // when
    final List<Change> changes = javers.findChanges(QueryBuilder.byInstanceId("Bob", Employee.class).build());
    final String changeLog = javers.processChangeList(changes, new SimpleTextChangeLog());

    // then
    System.out.println(changeLog);
  }
}
