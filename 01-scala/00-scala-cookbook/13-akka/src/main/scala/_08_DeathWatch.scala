import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Terminated}

/*
Monitoring the Death of an Actor with watch

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s09.html

Parent actor is notified when child actor dies
 */
object _08_DeathWatch {

  class Kenny extends Actor {
    override def receive: Receive = {
      case s: String => println("Kenny received a message: " + s)
      case _ => println(s"unknown message")
    }
  }

  class Parent extends Actor {
    // start Kenny as a child, then keep an eye on it
    val kenny = context.actorOf(Props[Kenny], name = "Kenny")
    context.watch(kenny) // watch method

    override def receive: Receive = {
      case Terminated(kenny) => println("Parent watch(kenny); Parent is notified when child dies")
      case _ => println("Parent received a message")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DeathWatch")

    // create the Parent that will create Kenny
    val parent = system.actorOf(Props[Parent], name = "parent")

    // lookup kenny, then kill it
    val kenny = system.actorSelection("/user/parent/Kenny")
    kenny ! PoisonPill

    Thread.sleep(1000)
    println("calling system.terminate")
    system.terminate
  }
}
/*
Parent watch(kenny); Parent is notified when child dies
calling system.terminate
 */
