
class Watch(brand: String, initialTime: Long) {
  def getTime(): Long = System.currentTimeMillis() - initialTime
}

object WatchUser {
  def main(args: Array[String]): Unit = {
    val expensiveWatch = new Watch("Expensive brand", 1000L) with Alarm with Notifier {
      override def trigger(): String = "Expensive brand: The alarm was triggered."

      override val notificationMessage: String = "Alarm is running!"

      override def clear(): Unit = {
        println("Alarm cleared.")
      }
    }

    val cheapWatch = new Watch("Cheap brand", 1000L) with Alarm {
      override def trigger(): String = "Cheap brand: The alarm was triggered."
    }

    // show some watch usage
    println(expensiveWatch.trigger())
    expensiveWatch.printNotification()
    println(s"The time is ${expensiveWatch.getTime()}")
    expensiveWatch.clear()

    println(cheapWatch.trigger())
    println("Cheap watches cannot manually stop the alarm...")
  }
}
/*
Expensive brand: The alarm was triggered.
Alarm is running!
The time is 1529081589975
Alarm cleared.
Cheap brand: The alarm was triggered.
Cheap watches cannot manually stop the alarm...
 */
