import akka.actor.{Actor, ActorSystem, Props}

/*
Creating an Actor Whose Class Constructor Requires

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s03.html
 */
object _02_ActorConstructorArgs {

  // constructor changed to take a parameter
  class HelloActor(myName: String) extends Actor {
    override def receive: Receive = {
      // println statements changed to show the name
      case "hello" => println(s"hello from $myName")
      case _ => println(s"'hub?', said $myName")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    // use a different version of the Props constructor
    val helloactor = system.actorOf(Props(new HelloActor("Tom")), name = "helloactor")
    helloactor ! "hello"
    helloactor ! "buenos dias"
    system.terminate
  }
}

/*
hello from Tom
'hub?', said Tom
 */
