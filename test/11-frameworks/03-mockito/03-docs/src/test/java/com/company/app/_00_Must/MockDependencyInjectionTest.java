package com.company.app._00_Must;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;

/*
Testing return methods

return method `Address AddressRetriever.retrieve()` - mock private field Http http of AddressRetriever
 */
public class MockDependencyInjectionTest {
  @FunctionalInterface
  interface Http {
    String get(String url) throws IOException;
  }

  class HttpImpl implements Http {
    public String get(String url) throws IOException {
      final CloseableHttpClient client = HttpClients.createDefault();
      HttpGet request = new HttpGet(url);
      final CloseableHttpResponse response = client.execute(request);
      try {
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
      } finally {
        response.close();
      }
    }
  }

  @Data
  @AllArgsConstructor
  class Address {
    private String houseNumber;
    private String road;
    private String city;
    private String state;
    private String zip;
  }

  class AddressRetriever {
    private Http http = new HttpImpl();

    public Address retrieve(double latitude, double longitude) throws IOException, ParseException {
      String parms = String.format("lat=%.6f&lon=%.6f", latitude, longitude);
      String response = http.get(
          "http://open.mapquestapi.com/nominatim/v1/reverse?format=json&"
              + parms);
      JSONObject obj = (JSONObject) new JSONParser().parse(response);
      JSONObject address = (JSONObject) obj.get("address");
      String country = (String) address.get("country_code");
      if (!country.equals("us"))
        throw new UnsupportedOperationException(
            "cannot support non-US addresses at this time");
      String houseNumber = (String) address.get("house_number");
      String road = (String) address.get("road");
      String city = (String) address.get("city");
      String state = (String) address.get("state");
      String zip = (String) address.get("postcode");
      return new Address(houseNumber, road, city, state, zip);
    }
  }

  @Mock
  private Http http;
  @InjectMocks
  private AddressRetriever retriever;

  @Before
  public void setup() {
    retriever = new AddressRetriever();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
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
    then(address.road).isEqualTo("North Tejon Street");
    then(address.city).isEqualTo("Colorado Springs");
    then(address.state).isEqualTo("Colorado");
    then(address.zip).isEqualTo("80903");
  }
}
