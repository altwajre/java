/*
Composing with self-types

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/419a5316-9144-4612-a77b-cbc6be34c437.xhtml

 */

// Any class that mixes in AlarmNotifier should also mix in Notifier.
trait AlarmNotifier {
  this: Notifier =>
  def trigger(): String
}

object SelfTypeWatchUser {
  def main(args: Array[String]): Unit = {
    val watch = new Watch("alarm with notification", 1000L) with AlarmNotifier with Notifier {
      override def trigger(): String = "Alarm triggered."

      override val notificationMessage: String = "The notification."

      override def clear(): Unit = {
        println("Alarm cleared.")
      }
    }

    println(watch.trigger())
    watch.printNotification()
    println(s"The time is ${watch.getTime()}")
    watch.clear()
  }
}
/*
Alarm triggered.
The notification.
The time is 1529104192822
Alarm cleared.
 */
