package com.company.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {
  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    Person person = new Person();
    person.setName("Will");
    person.setAge(28);
    person.setFriends(new String[]{"Tom", "Dick", "Harry"});

    person.setBooks(Arrays.asList("Java", "Python"));

    Map<String, String> keys = new HashMap<>();
    keys.put("1", "Apple");
    keys.put("2", "Orange");
    person.setKeys(keys);

    Product product = new Product();
    product.setName("product_1");
    product.setUri("product_uri");

    ProductContainer productContainer = new ProductContainer();
    productContainer.setKey("product_key");
    productContainer.setProduct(product);

    Offer offer = new Offer();
    offer.setName("offer_1");
    offer.setUri("offer_uri");

    OfferContainer offerContainer = new OfferContainer();
    offerContainer.setKey("offer_key");
    offerContainer.setOffer(offer);

    offer.setProducts(Arrays.asList(productContainer));

    person.setOffers(Arrays.asList(offerContainer));

    System.out.println("# Pojo to Json");
    JsonNode customerJson = mapper.convertValue(person, JsonNode.class);
    System.out.println(customerJson);

    System.out.println("# Json to Pojo");
    Person jsonToPojo = mapper.treeToValue(customerJson, Person.class);
    System.out.println(jsonToPojo);

    System.out.println("# Set offer pojo uri=null");
    person.getOffers().get(0).getOffer().setUri(null);
    System.out.println(person);

    System.out.println("# Pojo to Json, offer.uri should be excluded in json");
    JsonNode customerJson2 = mapper.convertValue(person, JsonNode.class);
    System.out.println(customerJson2);

  }
}
/*
# Pojo to Json
{"name":"Will","age":28,"friends":["Tom","Dick","Harry"],"books":["Java","Python"],"keys":{"1":"Apple","2":"Orange"},"offers":[{"key":"offer_key","offer":{"name":"offer_1","products":[{"key":"product_key","product":{"name":"product_1","uri":"product_uri"}}],"uri":"offer_uri"}}]}
# Json to Pojo
Person(id=null, name=Will, age=28, friends=[Tom, Dick, Harry], books=[Java, Python], keys={1=Apple, 2=Orange}, offers=[OfferContainer(key=offer_key, offer=Offer(name=offer_1, products=[ProductContainer(key=product_key, product=Product(name=product_1, uri=product_uri))], uri=offer_uri))])
# Set offer pojo uri=null
Person(id=null, name=Will, age=28, friends=[Tom, Dick, Harry], books=[Java, Python], keys={1=Apple, 2=Orange}, offers=[OfferContainer(key=offer_key, offer=Offer(name=offer_1, products=[ProductContainer(key=product_key, product=Product(name=product_1, uri=product_uri))], uri=null))])
# Pojo to Json, offer.uri should be excluded in json
{"name":"Will","age":28,"friends":["Tom","Dick","Harry"],"books":["Java","Python"],"keys":{"1":"Apple","2":"Orange"},"offers":[{"key":"offer_key","offer":{"name":"offer_1","products":[{"key":"product_key","product":{"name":"product_1","uri":"product_uri"}}]}}]}
 */
