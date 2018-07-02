package com.godaddy.ecomm.test.stargrade;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class SimpleTest {
    @Test
    public void should_equal_test() {
        String actual = "foo";
        String expected = "foo";
        assertThat(actual).isEqualTo(expected);
    }
}
