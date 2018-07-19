
/*
Traits as classes

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/16f6d668-2609-432a-9427-4d8034ae9a9c.xhtml

Traits can also be seen from the perspective of classes.
They implement all methods and have only one constructor that does not accept any parameters.
 */

trait Beeper {
  def beep(times: Int): Unit = {
    1 to times foreach(i => println(s"Beep number: $i"))
  }
}

object TraitsAsClasses {
  def main(args: Array[String]): Unit = {
    val beeper = new Beeper {}
    beeper.beep(3)
  }
}
/*
Beep number: 1
Beep number: 2
Beep number: 3
 */
