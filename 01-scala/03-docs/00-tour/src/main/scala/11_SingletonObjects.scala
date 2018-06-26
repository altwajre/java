/*
Singleton Objects

https://docs.scala-lang.org/tour/singleton-objects.html

An object is a class that has exactly one instance.
It is created lazily when it is referenced, like a lazy val.
 */

import scala.math._

class Project(name: String, daysToComplete: Int)

object SingletonObjects {
  def main(args: Array[String]) = {
    println("# Defining a singleton object")
    object Logger {
      def info(message: String) = println(s"INFO: $message")
    }

    val java = new Project("Java", 1)
    Logger.info("Create Java project")

    println("# Companion objects: an object with same name as a class")

    case class Circle(radius: Double) {
      import Circle._ // import the object Circle
      def area: Double = calculateArea(radius)
    }

    object Circle {
      private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
    }

    val circle = new Circle(5.0)
    println(circle.area)

    println("## Companion object contains factory methods")

    class Email(val username: String, val domainName: String)

    object Email {
      def fromString(emailString: String): Option[Email] = {
        emailString.split('@') match {
          case Array(a, b) => Some(new Email(a, b))
          case _ => None
        }
      }
    }

    val scalaEmail = Email.fromString("scala@gmail.com")
    println(scalaEmail.get.username)
    scalaEmail match {
      case Some(email) => println(s"""Registered an email
      |Username: ${email.username}
      |Domain name: ${email.domainName}""")
      case None => println("Error: could not parse email")
    }
  }
}
/*
# Defining a singleton object
INFO: Create Java project
# Companion objects: an object with same name as a class
78.53981633974483
## Companion object contains factory methods
scala
Registered an email
      |Username: scala
      |Domain name: gmail.com
 */
