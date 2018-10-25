package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

enum Race {
    HOBBIT,
    MAIA,
    ELF,
    DWARF,
    MAN
}
@Getter
@Setter
@AllArgsConstructor
class TolkienCharacter {
    private String name;
    private int age;
    private Race race;
}
public class AssertjTest {
    TolkienCharacter frodo = new TolkienCharacter("Frodo", 8, Race.HOBBIT);
    TolkienCharacter sam = new TolkienCharacter("Sam", 38, Race.HOBBIT);
    TolkienCharacter pippin = new TolkienCharacter("Pippin", 8, Race.HOBBIT);
    TolkienCharacter merry = new TolkienCharacter("Merry", 8, Race.HOBBIT);
    TolkienCharacter aragorn = new TolkienCharacter("Aragorn", 8, null);
    TolkienCharacter legolas = new TolkienCharacter("Legolas", 1000, Race.ELF);
    TolkienCharacter boromir = new TolkienCharacter("Boromir", 37, Race.MAN);

    TolkienCharacter sauron = new TolkienCharacter("Sauron", 8, null);
    TolkienCharacter elrond = new TolkienCharacter("Elrond", 8, null);

    List<TolkienCharacter> fellowshipOfTheRing = Arrays.asList(new TolkienCharacter("Gandalf", 8, null),
            boromir, legolas, sam, frodo, pippin, merry, aragorn);

    // String specific assertions
    @Test
    public void string_specific_assertions(){
        assertThat(frodo.getName()).isEqualTo("Frodo");
        assertThat(frodo.getName()).startsWith("Fro")
                .endsWith("do")
                .isEqualToIgnoringCase("frodo");
    }

    // collection specific assertions
    @Test
    public void collection_specific_assertions(){
        assertThat(fellowshipOfTheRing).hasSize(8)
                .contains(frodo, sam)
                .doesNotContain(sauron);
    }

    // Java 8 exception assertion
    @Test
    public void exception_assertion(){
        assertThatThrownBy(() -> { throw new Exception("boom!"); })
                .isInstanceOf(Exception.class)
                .hasMessageContaining("boom");
    }

    // Java 8 BDD style exception assertion
    @Test
    public void bdd_style_exception_assertion(){
        Throwable thrown = catchThrowable(() -> {throw new Exception("boom!");});
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("boom!");
    }

    // using extracting magical feature to check fellowshipOfTheRing characters name
    @Test
    public void check_name_field_values_in_collection(){
        assertThat(fellowshipOfTheRing).extracting("name")
                .contains("Boromir", "Gandalf", "Frodo", "Legolas")
                .doesNotContain("Sauron", "Elrond");
    }

    // Extracting with Java 8 love (type safe)
    @Test
    public void check_name_field_values_in_collection_with_type_safe(){
        assertThat(fellowshipOfTheRing).extracting(TolkienCharacter::getName)
                .contains("Boromir", "Gandalf", "Frodo", "Legolas")
                .doesNotContain("Sauron", "Elrond");
    }

    // Extracting multiple values at once (using tuple to group them)
    @Test
    public void extracting_multiple_fields_values_at_once(){
        assertThat(fellowshipOfTheRing).extracting("name", "age", "race")
                .contains(tuple("Boromir", 37, Race.MAN), tuple("Sam", 38, Race.HOBBIT), tuple("Legolas", 1000, Race.ELF));
    }

    // filter collection before assertion
    @Test
    public void filter_collection_before_assertion(){
        assertThat(fellowshipOfTheRing).filteredOn("race", Race.HOBBIT)
                .contains(sam, frodo, pippin, merry);
    }

    // filter collection with Java 8 Predicate
    @Test
    public void filter_collection_with_java8_predicate(){
        assertThat(fellowshipOfTheRing).filteredOn(c -> c.getName().contains("o"))
                .containsOnly(aragorn, frodo, legolas, boromir);
    }

    // combining filtering and extraction
    @Test
    public void combining_filtering_and_extraction(){
        assertThat(fellowshipOfTheRing).filteredOn(c -> c.getName().contains("o"))
                .containsOnly(aragorn, frodo, legolas, boromir)
                .extracting(c -> c.getRace())
                .contains(Race.HOBBIT, Race.ELF, Race.MAN);
    }

    // test contains
    @Test
    public void contains_test(){
        String s1 = "This is awesome!";
        assertThat(s1).contains("awesome");
    }
}
