
/*
Traits as interfaces

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/5dbd60f7-d1c2-43b1-8d75-8e6bf41491d3.xhtml

trait is called a mixin.
 */

// interface
trait Alarm {
  def trigger(): String
}

// abstract class
trait Notifier {
  val notificationMessage: String
  def printNotification(): Unit = {
    println(notificationMessage)
  }
  def clear()
}
