package com.company.app;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

class Shape {
  private List<Entity> entities;
  public Shape(List<Entity> entities) {
    this.entities = entities;
  }
  public List<Entity> getEntities() { return entities; }
}
class Entity {
}
class Circle extends Entity {
  private Map<String, Integer> center;
  private Integer radius;

  public Circle(Map<String, Integer> center, Integer radius){
    this.center = center;
    this.radius = radius;
  }

  public Map<String, Integer> getCenter() {
    return center;
  }

  public Integer getRadius() {
    return radius;
  }
}
class Line extends Entity {
  private Map<String, Integer> start;
  private Map<String, Integer> finish;

  Line(Map<String, Integer> start, Map<String, Integer> finish) {
    this.start = start;
    this.finish = finish;
  }

  public Map<String, Integer> getStart() {
    return start;
  }

  public Map<String, Integer> getFinish() {
    return finish;
  }
}
class Label extends Entity {
  private Map<String, Integer> start;
  private Integer color;
  private String text;

  Label(Map<String, Integer> start, Integer color, String text) {
    this.start = start;
    this.color = color;
    this.text = text;
  }

  public Map<String, Integer> getStart() {
    return start;
  }

  public Integer getColor() {
    return color;
  }

  public String getText() {
    return text;
  }
}
class MyConstructor extends Constructor {
  public MyConstructor() {
    this.yamlConstructors.put(new Tag("tag:clarkevans.com,2002:shape"), new ConstructShape());
    this.yamlConstructors.put(new Tag("tag:clarkevans.com,2002:circle"), new ConstructCircle());
    this.yamlConstructors.put(new Tag("tag:clarkevans.com,2002:line"), new ConstructLine());
    this.yamlConstructors.put(new Tag("tag:clarkevans.com,2002:label"), new ConstructLabel());
  }
  class ConstructShape extends AbstractConstruct {
    @Override
    public Object construct(Node node) {
      SequenceNode snode = (SequenceNode) node;
      List<Entity> values = (List<Entity>) constructSequence(snode);
      Shape shape = new Shape(values);
      return shape;
    }
  }
  class ConstructCircle extends AbstractConstruct {
    @Override
    public Object construct(Node node) {
      MappingNode mnode = (MappingNode) node;
      Map<Object, Object> values = constructMapping(mnode);
      Circle circle = new Circle((Map<String, Integer>) values.get("center"),
          (Integer) values.get("radius"));
      return circle;
    }
  }
  class ConstructLine extends AbstractConstruct {
    @Override
    public Object construct(Node node) {
      MappingNode mnode = (MappingNode) node;
      Map<Object, Object> values = constructMapping(mnode);
      Line line = new Line((Map<String, Integer>) values.get("start"),
          (Map<String, Integer>) values.get("finish"));
      return line;    }
  }
  class ConstructLabel extends AbstractConstruct {
    @Override
    public Object construct(Node node) {
      MappingNode mnode = (MappingNode) node;
      Map<Object, Object> values = constructMapping(mnode);
      Label label = new Label((Map<String, Integer>) values.get("start"),
          (Integer)values.get("color"), (String)values.get("text"));
      return label;
    }
  }
}
public class GlobalTagsPojosTest {

  /*
  yaml:
  #Example 2.24. Global Tags
  %TAG ! tag:clarkevans.com,2002:
  --- !shape
    # Use the ! handle for presenting
    # tag:clarkevans.com,2002:circle
  - !circle
    center: &ORIGIN {x: 73, y: 129}
    radius: 7
  - !line
    start: *ORIGIN
    finish: { x: 89, y: 102 }
  - !label
    start: *ORIGIN
    color: 0xFFEEBB
    text: Pretty vector drawing.
   */
  public static void globalTags() throws FileNotFoundException {
    String pathname = "src/main/resources/spec/06-global-tags-pojos.yaml";
    InputStream inputStream = new FileInputStream(new File(pathname));
    Yaml yaml = new Yaml(new MyConstructor());
    Shape shape = (Shape)yaml.load(inputStream);

    System.out.println(shape);

    Circle circle = (Circle) shape.getEntities().get(0);
    System.out.println("circle radius: "+circle.getRadius());

    Line line = (Line) shape.getEntities().get(1);
    System.out.println("line start: " + line.getStart());

    Label label = (Label) shape.getEntities().get(2);
    System.out.println("label text: " + label.getText());
  }
  /*
  output:
  com.company.app.Shape@4cdf35a9
  circle radius: 7
  line start: {x=73, y=129}
  label text: Pretty vector drawing.
   */
}
