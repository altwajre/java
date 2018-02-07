package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.junit.Test;

import javax.persistence.Id;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Data
@AllArgsConstructor
class Person {
  @Id
  private String login;
  private String name;
}
public class BasicCommitExample {
  // Commit Changes
  @Test
  public void shouldCommitToJaversRepository() {
    // given
    final Javers javers = JaversBuilder.javers().build();
    final Person robert = new Person("bob", "Robert Martin");
    // persist initial commit
    javers.commit("user", robert);

    // make some changes
    robert.setName("Robert C.");
    // persist again
    javers.commit("user", robert);

    // when
    final List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.byInstanceId("bob", Person.class).build());

    // then
    // there should be two Snapshots with Bob's state
    assertThat(snapshots).hasSize(2);

    final String json = javers.getJsonConverter().toJson(snapshots);
    System.out.println(json);
  }
/*
[
  {
    "commitMetadata": {
      "author": "user",
      "properties": [],
      "commitDate": "2017-09-20T18:17:55.248",
      "id": 2.00
    },
    "globalId": {
      "entity": "com.company.app.Person",
      "cdoId": "bob"
    },
    "state": {
      "name": "Robert C.",
      "login": "bob"
    },
    "changedProperties": [
      "name"
    ],
    "type": "UPDATE",
    "version": 2
  },
  {
    "commitMetadata": {
      "author": "user",
      "properties": [],
      "commitDate": "2017-09-20T18:17:55.178",
      "id": 1.00
    },
    "globalId": {
      "entity": "com.company.app.Person",
      "cdoId": "bob"
    },
    "state": {
      "name": "Robert Martin",
      "login": "bob"
    },
    "changedProperties": [
      "name",
      "login"
    ],
    "type": "INITIAL",
    "version": 1
  }
]
 */

  @Test
  public void shouldListStateHistory() {
    // given
    final Javers javers = JaversBuilder.javers().build();

    final Person robert = new Person("bob", "Robert Martin");
    javers.commit("user", robert);

    // make some changes
    robert.setName("Robert C.");
    javers.commit("user", robert);

    // when
    // list state history - last 10 snapshots
    final List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.byInstanceId("bob", Person.class).limit(10).build());

    // then
    assertThat(snapshots).hasSize(2);
    final CdoSnapshot newState = snapshots.get(0);
    final CdoSnapshot oldState = snapshots.get(1);
    assertThat(oldState.getPropertyValue("name")).isEqualTo("Robert Martin");
    assertThat(newState.getPropertyValue("name")).isEqualTo("Robert C.");
  }

  @Test
  public void shouldListChangeHistory() {
    // given
    final Javers javers = JaversBuilder.javers().build();
    final Person robert = new Person("bob", "Robert Martin");
    javers.commit("user", robert);

    robert.setName("Robert C.");
    javers.commit("user", robert);

    // when
    // list change history
    final List<Change> changes = javers.findChanges(QueryBuilder.byInstanceId("bob", Person.class).build());

    // then
    assertThat(changes).hasSize(1);

    final ValueChange change = (ValueChange)changes.get(0);

    assertThat(change.getPropertyName()).isEqualTo("name");
    assertThat(change.getLeft()).isEqualTo("Robert Martin");
    assertThat(change.getRight()).isEqualTo("Robert C.");
  }

}
