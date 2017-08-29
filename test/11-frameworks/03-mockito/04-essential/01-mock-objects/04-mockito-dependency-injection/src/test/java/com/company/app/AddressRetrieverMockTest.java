package com.company.app;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class AddressRetrieverMockTest {

  // 1. Create a mock instance using the @Mock annotation
  @Mock
  private Http http;
  // 2. Declare a target instance variable annotated with @InjectMocks
  @InjectMocks
  private AddressRetriever retriever;

  @Before
  public void createRetriever() {
    retriever = new AddressRetriever();
    // 3. After instantiating the target instance, call below
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void answersAppropriateAddressForValidCoordinatesBDD() throws IOException, ParseException {
    // given
    given(http.get(contains("lat=18.000000&lon=28.000000")))
        .willReturn(
            "{\"address\":{"
                + "\"house_number\":\"324\","
                + "\"road\":\"North Tejon Street\","
                + "\"city\":\"Colorado Springs\","
                + "\"state\":\"Colorado\","
                + "\"postcode\":\"80903\","
                + "\"country_code\":\"us\"}"
                + "}"
        );

    // when
    final Address address = retriever.retrieve(18.0, 28.0);

    // then
    then(address.houseNumber).isEqualTo("324");
  }

  @Test
  public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
    // Arrange - given
    /*
    when() is called to setup the expectations for the test.
    thenReturn() is called (returns the specified value) when the expectation is met
     */
    when(http.get(contains("lat=18.000000&lon=28.000000")))
        .thenReturn(
            "{\"address\":{"
                + "\"house_number\":\"324\","
                + "\"road\":\"North Tejon Street\","
                + "\"city\":\"Colorado Springs\","
                + "\"state\":\"Colorado\","
                + "\"postcode\":\"80903\","
                + "\"country_code\":\"us\"}"
                + "}"
        );

    // Act - when
    final Address address = retriever.retrieve(18.0, 28.0);

    // Assert - then
    assertThat(address.houseNumber, equalTo("324"));
    assertThat(address.road, equalTo("North Tejon Street"));
    assertThat(address.city, equalTo("Colorado Springs"));
    assertThat(address.state, equalTo("Colorado"));
    assertThat(address.zip, equalTo("80903"));
  }
}
