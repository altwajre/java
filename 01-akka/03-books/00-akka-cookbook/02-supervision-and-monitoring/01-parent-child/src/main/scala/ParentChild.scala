import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case object CreateChild
case class Greet(msg: String)

class ChildActor extends Actor {
  override def receive: Receive = {
    case Greet(msg) => {
      println(s"My parent[${self.path.parent}] greeted to me [${self.path}] $msg")
    }
    case greeting: String => {
      println(s"Parent greeted child $greeting")
    }
  }
}

class ParentActor extends Actor {
  override def receive: Receive = {
    case CreateChild => {
      // create a child actor
      val child = context.actorOf(Props[ChildActor], "child")
      child ! Greet("Hello Child")
      child ! "Hi Child"
    }
  }
}

object ParentChild extends App {
  private val system = ActorSystem("ParentChild")
  private val parent: ActorRef = system.actorOf(Props[ParentActor], "parent")
  parent ! CreateChild

  system.terminate()
}
/*
My parent[akka://ParentChild/user/parent] greeted to me [akka://ParentChild/user/parent/child] Hello Child
Parent greeted child Hi Child
 */
