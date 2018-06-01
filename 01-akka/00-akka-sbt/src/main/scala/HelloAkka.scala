import akka.actor.{Actor, ActorRef, ActorSystem, Props}

// Define Actor Messages
case class WhoToGreet(who: String)

// Define Greeter Actor
class Greeter extends Actor {
  def receive = {
    case WhoToGreet(who) => {
      println(s"Hello $who")
    }
  }
}

case class FooGreet(msg: String)
class Foo extends Actor {
  override def receive: Receive = {
    case FooGreet(msg) => {
      println(msg)
    }
    case _ =>
      println("Unknown msg")
  }
}

// Right click HelloAkka, click Run
object HelloAkka extends App {
  // Create the 'hello akka' actor system
  val system = ActorSystem("Hello-Akka")
  // Create the 'greeter' actor
  val greeter = system.actorOf(Props[Greeter], "greeter")
  // Send WhoToGreet Message to actor
  greeter ! WhoToGreet("Akka")
  // Hello Akka

  private val foo: ActorRef = system.actorOf(Props[Foo], "Foo")
//  foo ! FooGreet("Foo")
  // send WhoToGreet that is not in class Foo
  foo ! WhoToGreet("Foo")
  // Unknown msg

  //shutdown actorsystem
  system.terminate()

}
/*
Unknown msg
Hello Akka
 */