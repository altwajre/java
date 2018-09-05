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

    ProductContainer productContainer = new ProductContainer();
    productContainer.setKey("abc");
    productContainer.setProduct(product);

    Offer offer = new Offer();
    offer.setName("offer_1");

    OfferContainer offerContainer = new OfferContainer();
    offerContainer.setKey("123");
    offerContainer.setOffer(offer);

    offer.setProducts(Arrays.asList(productContainer));

    person.setOffers(Arrays.asList(offerContainer));

    System.out.println("# Pojo to JsonNode");
    JsonNode customerJson = mapper.convertValue(person, JsonNode.class);
    System.out.println(customerJson);

    System.out.println("# JsonNode to Pojo");
    Person jsonToPojo = mapper.treeToValue(customerJson, Person.class);
    System.out.println(jsonToPojo);

  }
}
/*
output:
# Pojo to JsonNode
{"id":null,"name":"Will","age":28,"friends":["Tom","Dick","Harry"],"books":["Java","Python"],"keys":{"1":"Apple","2":"Orange"},"offers":[{"key":"123","offer":{"name":"offer_1","products":[{"key":"abc","product":{"name":"product_1"}}]}}]}
# JsonNode to Pojo
Person(id=null, name=Will, age=28, friends=[Tom, Dick, Harry], books=[Java, Python], keys={1=Apple, 2=Orange}, offers=[OfferContainer(key=123, offer=Offer(name=offer_1, products=[ProductContainer(key=abc, product=Product(name=product_1))]))])
 */
