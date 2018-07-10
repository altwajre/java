/*
Composing with self-types

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/419a5316-9144-4612-a77b-cbc6be34c437.xhtml

 */

object SelfTypeWatchUser {
  trait Notifier {
    val notificationMessage: String

    def printNotification(): Unit = {
      System.out.println(notificationMessage)
    }

    def clear()
  }

  // Any class that mixes in AlarmNotifier should also mix in Notifier.
  trait AlarmNotifier {
    this: Notifier => // dependency
    def trigger(): String
  }

  class Watch(brand: String, initialTime: Long) {
    def getTime(): Long = System.currentTimeMillis() - initialTime
  }

  def main(args: Array[String]): Unit = {
    val watch = new Watch("alarm with notification", 1000L) with AlarmNotifier with Notifier {
      override def trigger(): String = "Alarm triggered by watch."

      override val notificationMessage: String = "The notification from watch."

      override def clear(): Unit = {
        println("Alarm cleared by watch.")
      }
    }

    println(watch.trigger())
    watch.printNotification()
    println(s"The time is ${watch.getTime()}")
    watch.clear()

    // code below WON'T work because without self-type Notifier
//    new Watch("my watch", 1L) with AlarmNotifier {
//      override def trigger(): String = ???
//    }
  }
}
/*
Alarm triggered by watch.
The notification from watch.
The time is 1530768019836
Alarm cleared by watch.
 */
