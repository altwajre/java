package com.company.app;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class AddressRetrieverTest {
  @Test
  public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
    // Arrange
    Http http = (String url) ->
        "{\"address\":{"
        + "\"house_number\":\"324\","
        + "\"road\":\"North Tejon Street\","
        + "\"city\":\"Colorado Springs\","
        + "\"state\":\"Colorado\","
        + "\"postcode\":\"80903\","
        + "\"country_code\":\"us\"}"
        + "}";

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