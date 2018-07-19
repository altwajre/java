package flyweight

import scala.collection.mutable.{ListBuffer, Map}

/*
The flyweight design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/4c85d374-5e43-4e00-8434-95d1ec1802ea.xhtml

The purpose of the flyweight is to minimize the memory usage with the help of an object
that shares as much data as possible with other similar objects.

Flyweight will obtain circles from cache - only 5 circles are created in following example
 */
object ScalaFlyweight {
  sealed abstract class Color
  case object Red extends Color
  case object Green extends Color
  case object Blue extends Color
  case object Yellow extends Color
  case object Magenta extends Color

  class Circle(color: Color) {
    println(s"Creating a circle with $color color.")

    override def toString: String = s"Circle($color)"
  }

  // Factory: circles will be flyweight objects
  object Circle {
    val cache = Map.empty[Color, Circle]

    // Use apply method as constructor
    // flyweight will obtain circles from cache
    def apply(color: Color): Circle =
      cache.getOrElseUpdate(color, new Circle(color))

    def circlesCreated(): Int = cache.size
  }

  class Graphic {
    val items = ListBuffer.empty[(Int, Int, Double, Circle)]

    def addCircle(x: Int, y: Int, radius: Double, circle: Circle): Unit = {
      items += ((x, y, radius, circle))
    }

    def draw(): Unit = {
      items.foreach {
        case (x, y, radius, circle) =>
          println(s"Drawing a circle at ($x, $y) with radius $radius: $circle")
      }
    }
  }

  def main(args: Array[String]) = {
    val graphic = new Graphic
    graphic.addCircle(1, 1, 1.0, Circle(Green))
    graphic.addCircle(1, 2, 1.0, Circle(Red))
    graphic.addCircle(2, 1, 1.0, Circle(Blue))
    graphic.addCircle(2, 2, 1.0, Circle(Green))
    graphic.addCircle(2, 3, 1.0, Circle(Yellow))
    graphic.addCircle(3, 2, 1.0, Circle(Magenta))
    graphic.addCircle(3, 3, 1.0, Circle(Blue))
    graphic.addCircle(4, 3, 1.0, Circle(Blue))
    graphic.addCircle(3, 4, 1.0, Circle(Yellow))
    graphic.addCircle(4, 4, 1.0, Circle(Red))

    graphic.draw()

    println(s"Total number of circle objects created: ${Circle.circlesCreated()}")

  }
}
