import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

/*
Starting an Actor

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s06.html

create actors at the system level and from within another actor
 */
object _05_StartActor {

  case class CreateChild(name: String)

  case class Name(name: String)

  class Child extends Actor {
    var name = "No name"

    override def postStop {
      println(s"D'oh! They killed me ($name): ${self.path}")
    }

    def receive = {
      case Name(name) => this.name = name
      case _ => println(s"Child $name got message")
    }
  }

  class Parent extends Actor {
    def receive = {
      case CreateChild(name) =>
        // Parent creates a new Child here
        println(s"Parent about to create Child ($name) ...")
        val child = context.actorOf(Props[Child], name = s"$name")
        child ! Name(name)
      case _ => println(s"Parent got some other message.")
    }
  }

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("ParentChildTest")
    val parent = actorSystem.actorOf(Props[Parent], name = "Parent")

    // send messages to Parent to create to child actors
    parent ! CreateChild("Jonathan")
    parent ! CreateChild("Jordan")
    Thread.sleep(500)

    // lookup Jonathan, then kill it
    println("Sending Jonathan a PoisonPill ...")
    val jonathan = actorSystem.actorSelection("/user/Parent/Jonathan")
    jonathan ! PoisonPill
    println("jonathan was killed")

    Thread.sleep(5000)
    actorSystem.terminate
  }
}
/*
Parent about to create Child (Jonathan) ...
Parent about to create Child (Jordan) ...
Sending Jonathan a PoisonPill ...
jonathan was killed
D'oh! They killed me (Jonathan): akka://ParentChildTest/user/Parent/Jonathan
D'oh! They killed me (Jordan): akka://ParentChildTest/user/Parent/Jordan
 */
