package null_object

import java.util.concurrent.ConcurrentLinkedQueue

import scala.util.Random

/*
The null object design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/2fe1c6ee-2dd9-4965-9c48-0e3964e82c68.xhtml

The purpose of the null object design pattern is to define an actual object that represents the null value and has neutral behavior.

Option[Message]
 */
case class Message(number: Int) {
  def print(): String = s"Message with number: $number"
}

class DataGenerator extends Runnable {
  val MAX_VAL = 10
  val MAX_TIME = 10000
  private var isStop = false
  private val queue: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()

  override def run(): Unit = {
    val random = new Random()
    while (!isStop) {
      Thread.sleep(random.nextInt(MAX_TIME))
      println(s"${Thread.currentThread().getName}: DataGenerator.run()")
      queue.add(random.nextInt(MAX_VAL))
    }
  }

  // Option
  def getMessage(): Option[Message] = Option(queue.poll()).map {
    case number => {
      Message(number)
    }
  }

  def requestStop() = this.synchronized {
    isStop = true
  }
}

object NullObject {
  val TIMES_TO_TRY = 10
  val MAX_TIME = 5000
  def main(args: Array[String]) = {
    val generator = new DataGenerator
    // start the generator in another thread
    new Thread(generator).start()
    val random = new Random()
    (0 to TIMES_TO_TRY).foreach {
      case time =>
        Thread.sleep(random.nextInt(MAX_TIME))
        println("Getting next message...")
        generator.getMessage().foreach(m => println(m.print()))
    }
    generator.requestStop()
  }
}
/*
Getting next message...
Getting next message...
Getting next message...
Thread-0: DataGenerator.run()
Getting next message...
Message with number: 8
Getting next message...
Thread-0: DataGenerator.run()
Getting next message...
Message with number: 3
Getting next message...
Thread-0: DataGenerator.run()
Getting next message...
Message with number: 6
Getting next message...
Thread-0: DataGenerator.run()
Getting next message...
Message with number: 1
Thread-0: DataGenerator.run()
Getting next message...
Message with number: 1
Thread-0: DataGenerator.run()
 */
