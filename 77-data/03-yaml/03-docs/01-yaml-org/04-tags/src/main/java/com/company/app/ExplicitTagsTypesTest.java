package com.company.app;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

class SomethingConstructor extends Constructor {
  public SomethingConstructor() {
    this.yamlConstructors.put(new Tag("!something"), new ConstructSomething());
  }

  private class ConstructSomething extends AbstractConstruct {
    public Object construct(Node node) {
      // convert to upper case
      String val = (String) constructScalar((ScalarNode) node);
      return val.toUpperCase().replace('\n', ' ').trim();
    }
  }
}

public class ExplicitTagsTypesTest {

  /*
  yaml:
  #Example 2.23. Various Explicit Tags
  ---
  not-date: !!str 2002-04-28

  picture: !!binary |
   R0lGODlhDAAMAIQAAP//9/X
   17unp5WZmZgAAAOfn515eXv
   Pz7Y6OjuDg4J+fn5OTk6enp
   56enmleECcgggoBADs=

  application specific tag: !something |
   The semantics of the tag
   above may be different for
   different documents.
   */
  public static void variousExplicitTags() throws FileNotFoundException {

    String pathname = "src/main/resources/spec/05-various-explicit-tags.yaml";
    InputStream inputStream = new FileInputStream(new File(pathname));
    Yaml yaml = new Yaml(new SomethingConstructor());
    Map<Object, Object> nativeData = (Map<Object, Object>)yaml.load(inputStream);
    System.out.println(nativeData);
    nativeData.entrySet().forEach(e -> {
      System.out.println(e.getKey() + ": " + e.getValue());
    });
  }
    /*
    output:
    {not-date=2002-04-28, picture=[B@deb6432, application specific tag=THE SEMANTICS OF THE TAG ABOVE MAY BE DIFFERENT FOR DIFFERENT DOCUMENTS.}
    not-date: 2002-04-28
    picture: [B@deb6432
    application specific tag: THE SEMANTICS OF THE TAG ABOVE MAY BE DIFFERENT FOR DIFFERENT DOCUMENTS.
     */
}
