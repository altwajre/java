package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.junit.Test;

import javax.persistence.Id;

import static org.assertj.core.api.Assertions.assertThat;

@Data
@AllArgsConstructor
class Person {
  @Id
  private String login;
  private String firstName;
  private String lastName;
}

public class CompareTwoEntityObjects {
  @Test
  public void shouldCompareTwoEntityObjects() {
    // given
    final Javers javers = JaversBuilder.javers().build();

    final Person tommyOld = new Person("tommy", "Tommy Smart", "Lee");
    final Person tommyNew = new Person("tommy", "Tommy C. Smart", "Lee");

    // when
    final Diff diff = javers.compare(tommyOld, tommyNew);

    // then
    // there should be one change of type {@link ValueChange}
    assertThat(diff.getChanges()).hasSize(1);
    final ValueChange change = diff.getChangesByType(ValueChange.class).get(0);

    assertThat(change.getPropertyName()).isEqualTo("firstName");
    assertThat(change.getAffectedGlobalId().value()).isEqualTo("com.company.app.Person/tommy");
    assertThat(change.getLeft()).isEqualTo("Tommy Smart");
    assertThat(change.getRight()).isEqualTo("Tommy C. Smart");

    System.out.println(diff);
  }
/*
Diff:
1. ValueChange{globalId:'com.company.app.Person/tommy', property:'name', oldVal:'Tommy Smart', newVal:'Tommy C. Smart'}
 */

  @Test
  public void changeMultipleFields() {
    // given
    final Javers javers = JaversBuilder.javers().build();

    final Person tommyOld = new Person("tommy", "Tommy Smart", "Lee");
    final Person tommyNew = new Person("tommy", "Tommy C. Smart", "Lee C");

    // when
    final Diff diff = javers.compare(tommyOld, tommyNew);

    // then
    // there should be one change of type {@link ValueChange}
    assertThat(diff.getChanges()).hasSize(2);

    final ValueChange change1 = diff.getChangesByType(ValueChange.class).get(0);
    assertThat(change1.getPropertyName()).isEqualTo("firstName");
    assertThat(change1.getAffectedGlobalId().value()).isEqualTo("com.company.app.Person/tommy");
    assertThat(change1.getLeft()).isEqualTo("Tommy Smart");
    assertThat(change1.getRight()).isEqualTo("Tommy C. Smart");

    System.out.println(diff);

    final String json = javers.getJsonConverter().toJson(diff);
    System.out.println(json);
  }
/*
Diff:
1. ValueChange{globalId:'com.company.app.Person/tommy', property:'firstName', oldVal:'Tommy Smart', newVal:'Tommy C. Smart'}
2. ValueChange{globalId:'com.company.app.Person/tommy', property:'lastName', oldVal:'Lee', newVal:'Lee C'}

{
  "changes": [
    {
      "changeType": "ValueChange",
      "globalId": {
        "entity": "com.company.app.Person",
        "cdoId": "tommy"
      },
      "property": "firstName",
      "left": "Tommy Smart",
      "right": "Tommy C. Smart"
    },
    {
      "changeType": "ValueChange",
      "globalId": {
        "entity": "com.company.app.Person",
        "cdoId": "tommy"
      },
      "property": "lastName",
      "left": "Lee",
      "right": "Lee C"
    }
  ]
}
 */
}
