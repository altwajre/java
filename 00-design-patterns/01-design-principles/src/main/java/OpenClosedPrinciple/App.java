package OpenClosedPrinciple;

import java.util.ArrayList;
import java.util.List;

/*
http://www.oodesign.com/open-close-principle.html

Intent:
Software entities like classes, modules and functions should be open for extension but closed for modifications.

GraphicEditor supports the Open Closed Principle
*/
abstract class Shape {
  abstract void draw();
}

class Rectangle extends Shape {
  @Override
  void draw() {
    System.out.println("Draw Rectangle");
  }
}

class Circle extends Shape {
  @Override
  void draw() {
    System.out.println("Draw Circle");
  }
}

// It supports the Open Closed Principle
class GraphicEditor {
  public void drawShape(Shape shape) {
    shape.draw();
  }
}

public class App {
  public static void main(String[] args) {
    List<Shape> shapes = new ArrayList<>();
    shapes.add(new Rectangle());
    shapes.add(new Circle());

    GraphicEditor editor = new GraphicEditor();
    shapes.forEach(shape -> {
      editor.drawShape(shape);
    });
  }
}
/*
Draw Rectangle
Draw Circle
 */
