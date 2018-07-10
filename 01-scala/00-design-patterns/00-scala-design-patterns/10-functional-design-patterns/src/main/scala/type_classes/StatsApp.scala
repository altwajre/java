package type_classes

import java.lang.Math.round

/*
The type class design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/75b02e7e-283b-4e5a-a11f-fe73c87530d6.xhtml

It is for adapter design pattern.
It allows us to implement ad-hoc polymorphism.
 */

// type class: trait that defines some operations
trait Number[T] {
  def plus(x: T, y: T): T
  def minus(x: T, y: T): T
  def divide(x: T, y: Int): T
  def multiply(x: T, y: T): T
}

// define implicitly available Number members for Int and Double.
object Number {
  implicit object DoubleNumber extends Number[Double] {
    override def plus(x: Double, y: Double): Double = x + y
    override def divide(x: Double, y: Int): Double = x / y
    override def multiply(x: Double, y: Double): Double = x * y
    override def minus(x: Double, y: Double): Double = x - y
  }

  implicit object IntNumber extends Number[Int] {
    override def plus(x: Int, y: Int): Int = x + y
    override def divide(x: Int, y: Int): Int =  {
      println(s"x: $x, y: $y")
      round(x.toDouble / y.toDouble).toInt
    }
    override def multiply(x: Int, y: Int): Int = x * y
    override def minus(x: Int, y: Int): Int = x - y
  }
}

object Stats {
  //  same as
  //  def mean[T](xs: Vector[T])(implicit ev: Number[T]): T =
  //    ev.divide(xs.reduce(ev.plus(_, _)), xs.size)
  def mean[T: Number](xs: Vector[T]): T =
    implicitly[Number[T]].divide(
      xs.reduce(implicitly[Number[T]].plus(_, _)),
      xs.size
    )

  // assumes the vector is sorted
  def median[T: Number](xs: Vector[T]): T =
    xs(xs.size / 2)

  def variance[T: Number](xs: Vector[T]): T = {
    val simpleMean = mean(xs)
    val sqDiff = xs.map {
      case x =>
        val diff = implicitly[Number[T]].minus(x, simpleMean)
        implicitly[Number[T]].multiply(diff, diff)
    }
    mean(sqDiff)
  }

}

object StatsApp {
  import Stats._
  def main(args: Array[String]): Unit = {
    val intVector = Vector(2, 3, 5, 4, 6)
    println(s"Mean (int): ${mean(intVector)}")
    println(s"Median (int): ${median(intVector)}")

    val doubleVector = Vector(1.5, 3.6, 5.0, 6.6, 10.9, 12.1, 17.3, 18.4, 19.2, 30.9, 36.6, 40.2, 42.3, 66.0)
    println(s"Mean (double): ${mean(doubleVector)}")
    println(s"Median (double): ${median(doubleVector)}")

  }
}
/*
x: 20, y: 5
Mean (int): 4
Median (int): 5
Mean (double): 22.185714285714287
Median (double): 18.4
 */
