package bAbstractfactory;

interface Shape {
}

interface ShapeFactory {
  Shape make(String shapeName);
  String[] getShapeNames();
}

public class ShapeFactoryApp {
  public static void main(String[] args) {

  }
}

//--------

class Square implements Shape {
}

class Circle implements Shape {
}

class ShapeFactoryImpl implements ShapeFactory {

  @Override
  public Shape make(String shapeName) {
    switch (shapeName.toLowerCase()) {
      case "circle":
        return new Circle();
      case "square":
        return new Square();
        default:
          throw new RuntimeException("Unknown Shape");
    }
  }

  @Override
  public String[] getShapeNames() {
    return new String[] {"circle", "square"};
  }
}
