package com.company.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/*
http://www.baeldung.com/jackson-map

Serialization converts a Java object into a stream of bytes, which cna be persisted or shared as needed.

Deserialization converts a stream of bytes into a Java object that we can use in code
 */
@Data
@AllArgsConstructor
class MyPair {
  private String first;
  private String second;

  public MyPair(String both) {
    String[] pairs = both.split("and");
    this.first = pairs[0].trim();
    this.second = pairs[1].trim();
  }

  @Override
  // annotate toString() with @JsonValue to ensure Jackson uses this custom toString() when serializing
  @JsonValue
  public String toString() {
    return first + " and " + second;
  }
}

class MyPairSerializer extends JsonSerializer<MyPair> {
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public void serialize(MyPair myPair, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
    StringWriter writer = new StringWriter();
    mapper.writeValue(writer, myPair);
    jsonGenerator.writeFieldName(writer.toString());
  }
}

class CustomClassTester {
  @JsonSerialize(keyUsing = MyPairSerializer.class)
  Map<MyPair, String> map;

  public void test() throws JsonProcessingException {
    map = new HashMap<>();
    MyPair key = new MyPair("Tom", "Dick");
    map.put(key, "Pair programming");
    ObjectMapper mapper = new ObjectMapper();
    final String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    System.out.println(jsonResult);
  }
}

class ComplexTester {
  @JsonSerialize(keyUsing = MapSerializer.class)
  Map<MyPair, MyPair> map = new HashMap<>();
  @JsonSerialize(keyUsing = MyPairSerializer.class)
  MyPair mapKey;
  @JsonSerialize(keyUsing = MyPairSerializer.class)
  MyPair mapValue;

  public void test() throws JsonProcessingException {
    mapKey = new MyPair("Tom", "Dick");
    mapValue = new MyPair("Java", "python");
    map.put(mapKey, mapValue);
    ObjectMapper mapper = new ObjectMapper();
    final String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    System.out.println(jsonResult);
  }
}

class MyPairDeserializer extends KeyDeserializer {
  @Override
  public Object deserializeKey(String key, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    return new MyPair(key);
  }
}

class ClassWithMap {
  @JsonProperty("map")
  @JsonDeserialize(keyUsing = MyPairDeserializer.class)
  Map<MyPair, String> map;

  @JsonCreator
  public ClassWithMap(Map<MyPair, String> map) {
    this.map = map;
  }
}

class ClassWithComplexMap {
  @JsonProperty("map")
  @JsonDeserialize(keyUsing = MyPairDeserializer.class)
  Map<MyPair, MyPair> map;

  @JsonCreator
  public ClassWithComplexMap(Map<MyPair, MyPair> map) {
    this.map = map;
  }

}

public class App {
  public static void main(String[] args) throws IOException {
    serializeSimple(); // Map<String, String> map
    serializeCustomClass(); // Map<MyPair, String> map
    serializeComplexMap(); // Map<MyPair, MyPair> map

    deserializeSimple(); // Map<MyPair, String> map
    deserializeComplexMap(); // Map<MyPair, MyPair> map
  }

  private static void deserializeComplexMap() throws IOException {
    String jsonInput = "{\"Tom and Dick\" : \"Java and python\"}";
    ObjectMapper mapper = new ObjectMapper();
    final ClassWithComplexMap classWithComplexMap = mapper.readValue(jsonInput, ClassWithComplexMap.class);
    System.out.println(classWithComplexMap.map);
  }
/*
{Tom and Dick=Java and python}
 */

  private static void deserializeSimple() throws IOException {
    String jsonInput = "{\"Tom and Dick\" : \"Pair programming\"}";
    ObjectMapper mapper = new ObjectMapper();
    final ClassWithMap classWithMap = mapper.readValue(jsonInput, ClassWithMap.class);
    // Verify deserialized map
    System.out.println(classWithMap.map);
  }
/*
{Tom and Dick=Pair programming}
 */

  private static void serializeComplexMap() throws JsonProcessingException {
    ComplexTester tester = new ComplexTester();
    tester.test();
  }
/*
{
  "Tom and Dick" : "Java and python"
}
 */

  private static void serializeCustomClass() throws JsonProcessingException {
    CustomClassTester tester = new CustomClassTester();
    tester.test();
  }
/*
{
  "Tom and Dick" : "Pair programming"
}
 */

  private static void serializeSimple() throws JsonProcessingException {
    Map<String, String> map = new HashMap<>();
    map.put("key", "value");

    ObjectMapper mapper = new ObjectMapper();

    final String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    System.out.println(jsonResult);
  }
/*
{
  "key" : "value"
}
 */
}
