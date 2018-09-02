package com.company.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class App {
  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    Person person = new Person();
    person.setName("Will");
    person.setAge(28);
    person.setFriends(new String[]{"Tom", "Dick", "Harry"});
    Map<String, String> keys = new HashMap<>();
    keys.put("1", "Apple");
    keys.put("2", "Orange");
    person.setKeys(keys);

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
{"id":null,"name":"Will","age":28,"friends":["Tom","Dick","Harry"],"keys":{"1":"Apple","2":"Orange"}}
# JsonNode to Pojo
Person(id=null, name=Will, age=28, friends=[Tom, Dick, Harry], keys={1=Apple, 2=Orange})
 */
