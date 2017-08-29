# Replacing Troublesome Behavior with Stubs

https://www.safaribooksonline.com/library/view/pragmatic-unit-testing/9781680500769/f_0086.html

We decide to use dependency injection which pass the stub to AddressRetriever instance, or inject it.
For now, we choose to inject the stub via a constructor on AddressRetriever.

> stub - dependency injection HTTP

add AddressRetriever constructor that takes HTTP as arg as below

```
public class AddressRetriever {
   private Http http;

   public AddressRetriever(Http http) {
      this.http = http;
   }
```

## Test

AddressRetrieverTest.answersAppropriateAddressForValidCoordinates()

> add smart stub

```
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
```
