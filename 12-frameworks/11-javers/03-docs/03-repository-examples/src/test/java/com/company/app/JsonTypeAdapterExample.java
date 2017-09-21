package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.json.BasicStringTypeAdapter;
import org.junit.Test;

import javax.persistence.Id;

@Data
@AllArgsConstructor
class StoredEntity {
  @Id
  private String id;
  private String algorithm;
  private String version;
  private String name;
}
class MyTypeAdapter extends BasicStringTypeAdapter {

  @Override
  public String serialize(Object o) {
    return o.toString();
  }

  @Override
  public Object deserialize(String s) {
    return s;
  }

  @Override
  public Class getValueType() {
    return String.class;
  }
}
public class JsonTypeAdapterExample {
  @Test
  public void shouldSerializeValueToJsonWithTypeAdapter() {
    // given
    final Javers javers = JaversBuilder.javers().registerValueTypeAdapter(new MyTypeAdapter()).build();

    // when
    String id = "myId";
    final StoredEntity entity1 = new StoredEntity(id, "alg1", "1.0", "name");
    final StoredEntity entity2 = new StoredEntity(id, "alg1", "1.0", "another");
    final Diff diff = javers.compare(entity1, entity2);

    // then
    final String json = javers.getJsonConverter().toJson(diff);
    System.out.println(json);
  }
/*
{
  "changes": [
    {
      "changeType": "ValueChange",
      "globalId": {
        "entity": "com.company.app.StoredEntity",
        "cdoId": "myId"
      },
      "property": "name",
      "left": "name",
      "right": "another"
    }
  ]
}
 */
}
