package com.company.app;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressRetrieverMockTest {
  @Test
  public void answersAppropriateAddressForValidCoordinatesBDD() throws IOException, ParseException {
    Http http = mock(Http.class);

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
    final AddressRetriever retriever = new AddressRetriever(http);
    final Address address = retriever.retrieve(18.0, 28.0);

    // then
    then(address.houseNumber).isEqualTo("324");
    then(address.road).isEqualTo("North Tejon Street");
    then(address.city).isEqualTo("Colorado Springs");
    then(address.state).isEqualTo("Colorado");
    then(address.zip).isEqualTo("80903");
  }

  @Test
  public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
    // Arrange - given

    /*
    mock(Http.class) tells Mockito to create a mock instance that implements the Http interface.
    This mock does all the dirty tracking and verifying work behind the scenes.
     */
    Http http = mock(Http.class);

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
    final AddressRetriever retriever = new AddressRetriever(http);
    final Address address = retriever.retrieve(18.0, 28.0);

    // Assert - then
    assertThat(address.houseNumber, equalTo("324"));
    assertThat(address.road, equalTo("North Tejon Street"));
    assertThat(address.city, equalTo("Colorado Springs"));
    assertThat(address.state, equalTo("Colorado"));
    assertThat(address.zip, equalTo("80903"));
  }
}
