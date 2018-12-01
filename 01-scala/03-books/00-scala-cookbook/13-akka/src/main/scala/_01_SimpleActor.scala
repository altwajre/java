import akka.actor.{Actor, ActorSystem, Props}

/*
Getting Started with a Simple Actor

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s02.html
 */
object _01_SimpleActor {
  class HelloActor extends Actor {
    override def receive: Receive = {
      case "hello" => println("hello back at you")
      case _ => println("huh?")
    }
  }

  def main(args: Array[String]): Unit = {
    // an actor needs an ActorSystem
    val system = ActorSystem("HelloSystem")

    // create and start the actor
    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")

    // send the actor two message
    helloActor ! "hello"
    helloActor ! "buenos dias"

    system.terminate
  }
}
/*
hello back at you
huh?
 */
