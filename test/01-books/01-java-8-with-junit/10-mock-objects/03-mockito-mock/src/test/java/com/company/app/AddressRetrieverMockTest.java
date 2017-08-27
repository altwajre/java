package com.company.app;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressRetrieverMockTest {
  @Test
  public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
    // Arrange - Mockito mock

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

    final AddressRetriever retriever = new AddressRetriever(http);

    // Act
    final Address address = retriever.retrieve(18.0, 28.0);

    // Assert
    assertThat(address.houseNumber, equalTo("324"));
    assertThat(address.road, equalTo("North Tejon Street"));
    assertThat(address.city, equalTo("Colorado Springs"));
    assertThat(address.state, equalTo("Colorado"));
    assertThat(address.zip, equalTo("80903"));
  }

}
