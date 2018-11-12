import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonHelper {
  private static ObjectMapper mapper = new ObjectMapper();

  public static JsonNode toJson(String jsonString) {
    try {
      return mapper.readTree(jsonString);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static<T> JsonNode toJson(T t){
    return mapper.valueToTree(t);
  }

  public static <T> T toPojo(String jsonString, Class<T> type) {
    return toPojo(toJson(jsonString), type);
  }

  public static <T> T toPojo(JsonNode jsonBody, Class<T> type) {
    try {
      return mapper.treeToValue(jsonBody, type);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T toList(String jsonString, TypeReference<T> typeReference) {
    try {
      return mapper.readValue(jsonString, typeReference);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
