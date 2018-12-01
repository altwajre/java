import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ResourceHelper {
  private static ObjectMapper mapper = new ObjectMapper();

  public static JsonNode get(String path) {
    try {
      return mapper.readTree(mapper
          .getClass()
          .getClassLoader()
          .getResourceAsStream(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
