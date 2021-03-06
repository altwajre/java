package com.company.app;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class AddressRetrieverTest {
  @Test
  public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
    // Arrange - stub
    Http http = (String url) -> {
      /*
      Add a guard to the stub that verifies the URL passed to the Http method get().
      If it doesn't contain the expected parameter string, fail the test.
       */
      if(!url.contains("lat=18.000000&lon=28.000000")) {
        fail("utl" + url + "does not contain correct params");
      }
      return "{\"address\":{"
          + "\"house_number\":\"324\","
          + "\"road\":\"North Tejon Street\","
          + "\"city\":\"Colorado Springs\","
          + "\"state\":\"Colorado\","
          + "\"postcode\":\"80903\","
          + "\"country_code\":\"us\"}"
          + "}";
    };

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
