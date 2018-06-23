/*
Hybrid ADTs

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/5f559517-ae41-4171-83f9-2034f7b97071.xhtml

Hybrid algebraic data types represent a combination of the sum and product ADTs.
This means that we can specific value constructors.

Sum ADT - because we have the specific Circle and Rectangle value constructors
Product ADT - because the constructors take extra parameters.
 */

// When drawing the shapes, we need to known their positions.
case class Point(x: Double, y: Double)
sealed abstract trait Shape
case class Circle(centre: Point, radius: Double) extends Shape
case class Rectangle(topLeft: Point, height: Double, width: Double) extends Shape

object Shape {
  def area(shape: Shape): Double = {
    shape match {
      case Circle(Point(x, y), radius) => Math.PI * Math.pow(radius, 2)
      case Rectangle(_, h, w) => h * w
    }
  }
}

object HybridADTs {
  def main(args: Array[String]) = {
    val circle = Circle(Point(1, 2), 2.5)
    val rect = Rectangle(Point(6, 7), 5, 6)

    println(s"The circle area is: ${Shape.area(circle)}")
    println(s"The rectangle area is: ${Shape.area(rect)}")
  }
}
/*
The circle area is: 19.634954084936208
The rectangle area is: 30.0
 */
