package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;

import javax.persistence.Id;
import java.util.List;

/*
https://javers.org/documentation/getting-started/
 */

@Data
@AllArgsConstructor
class Person {
  @Id
  private String id;
  private String name;
  private int age;
}

public class App {
  public static void main(String[] args) {
    objectDiff();

    objectAudit();
  }

  // https://javers.org/documentation/repository-examples/
  private static void objectAudit() {
    final Javers javers = JaversBuilder.javers().build();
    final Person person = new Person("test_id","Old", 38);
    // persist initial commit
    javers.commit("user", person);

    // make some changes
    person.setName("New");
    // persist again
    javers.commit("user", person);

    final List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.byInstanceId("test_id", Person.class).build());

    System.out.println(snapshots);

    final CdoSnapshot newState = snapshots.get(0);
    final CdoSnapshot oldState = snapshots.get(1);
    System.out.println(newState);
    System.out.println(oldState);
  }
/*
[Snapshot{commit:2.0, id:com.company.app.Person/test_id, version:2, (age:38, id:test_id, name:New)}, Snapshot{commit:1.0, id:com.company.app.Person/test_id, version:1, (age:38, id:test_id, name:Old)}]
Snapshot{commit:2.0, id:com.company.app.Person/test_id, version:2, (age:38, id:test_id, name:New)}
Snapshot{commit:1.0, id:com.company.app.Person/test_id, version:1, (age:38, id:test_id, name:Old)}
 */

  // https://javers.org/documentation/diff-examples/
  private static void objectDiff() {
    final Javers javers = JaversBuilder.javers().build();
    final Person tomOld = new Person("tom","Tom", 18);
    final Person tomNew = new Person("tom","Tommy", 28);
    final Diff diff = javers.compare(tomNew, tomOld);
    System.out.println(diff);
  }
}
/*
Diff:
1. ValueChange{globalId:'com.company.app.Person/', property:'name', oldVal:'Tommy', newVal:'Tom'}
2. ValueChange{globalId:'com.company.app.Person/', property:'age', oldVal:'28', newVal:'18'}
 */
