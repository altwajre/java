package lazy_init

import java.util.Properties

/*
Lazy initialization

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/1d8e43ad-5c19-4a98-b43f-e1cd54758401.xhtml

 */
object CircleUtils {
  val basicPi = 3.14
  // lazy initialization
  lazy val precisePi: Double = {
    System.out.println("Lazy load properties for the precise PI.")
    val props = new Properties()
    // resource file should have the same package
    props.load(getClass.getResourceAsStream("pi.properties"))
    props.getProperty("pi.high").toDouble
  }

  def area(radius: Double, isPrecise: Boolean = false): Double = {
    val pi: Double = if (isPrecise) precisePi else basicPi
    pi * Math.pow(radius, 2)
  }
}

object LazyInitialization {
  def main(args: Array[String]) = {
    System.out.println(s"The basic area for a circle with radius 2.5 is ${CircleUtils.area(2.5)}")
    System.out.println(s"The lazy load precise area for a circle with radius 2.5 is ${CircleUtils.area(2.5, true)}")
  }
}
/*
The basic area for a circle with radius 2.5 is 19.625
Lazy load properties for the precise PI.
The lazy load precise area for a circle with radius 2.5 is 19.634954084936208
 */
