import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case class DoubleValue(x: Int)

case object CreateChild

case object Send

case class Response(x: Int)

// child actor
class DoubleActor extends Actor {
  override def receive: Receive = {
    case DoubleValue(number) => {
      println(s"${self.path.name} Get the number $number")
      sender ! Response(number * 2)
    }
  }
}

class ParentActor extends Actor {
  val random = new scala.util.Random
  var childs = scala.collection.mutable.ListBuffer[ActorRef]()

  def receive = {
    case CreateChild =>
      childs ++= List(context.actorOf(Props[DoubleActor]))
    case Send =>
      println(s"Sending messages to child")
      childs.zipWithIndex map { case (child, value) =>
        child ! DoubleValue(random.nextInt(10))
      }
    case Response(x) => println(s"Parent: Response from child ${sender.path.name} is $x")
  }
}

object SendMesagesToChilds extends App {
  private val system = ActorSystem("SendMesagesToChilds")
  private val parent: ActorRef = system.actorOf(Props[ParentActor], "parent")
  parent ! CreateChild
  parent ! CreateChild
  parent ! CreateChild
  parent ! Send

  Thread.sleep(1000)
  system.terminate()
}
/*
Sending messages to child
$a Get the number 8
$b Get the number 9
$c Get the number 4
Parent: Response from child $a is 16
Parent: Response from child $c is 8
Parent: Response from child $b is 18
 */
