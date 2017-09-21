package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Data
@AllArgsConstructor
class Address{
  private String city;
  private String street;
}
public class CompareValueObjects {
  @Test
  public void shouldCompareTwoObjects() {
    // given
    final Javers javers = JaversBuilder.javers().build();

    final Address address1 = new Address("New York", "5th Ave");
    final Address address2 = new Address("New York", "6th Ave");

    // when
    final Diff diff = javers.compare(address1, address2);

    // then
    // there should be one change of type {@link ValueChange)
    final ValueChange change = diff.getChangesByType(ValueChange.class).get(0);

    assertThat(diff.getChanges()).hasSize(1);
    assertThat(change.getPropertyName()).isEqualTo("street");
    assertThat(change.getAffectedGlobalId().value()).isEqualTo("com.company.app.Address/");
    assertThat(change.getLeft()).isEqualTo("5th Ave");
    assertThat(change.getRight()).isEqualTo("6th Ave");

    System.out.println(diff);
  }
/*
Diff:
1. ValueChange{globalId:'com.company.app.Address/', property:'street', oldVal:'5th Ave', newVal:'6th Ave'}
 */
}
