import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class BecomeUnbeomeActor extends Actor {
  override def receive: Receive = {
    case true => {
      println("switch to isStateTrue")
      context.become(isStateTrue)
    }
    case false => {
      context.become(isStateFalse)
    }
    case _ => println("don't know what you want to say.")
  }

  def isStateTrue: Receive = {
    case msg: String => println(s"isStateTrue: $msg")
    case false => {
      println("switch to isStateFalse")
      context.become(isStateFalse)
    }
  }

  def isStateFalse: Receive = {
    case msg: Int => println(s"isStateFalse: $msg")
    case true => context.become(isStateTrue)
  }
}

object BecomeUnBecome extends App {
  private val system = ActorSystem("BecomeUnBecome")
  private val actor: ActorRef = system.actorOf(Props[BecomeUnbeomeActor])

  actor ! true
  actor ! "Hell how are you?"
  actor ! false
  actor ! 10101
  actor ! true
  actor ! "What do you do?"

  system.terminate()
}
/*
switch to isStateTrue
isStateTrue: Hell how are you?
switch to isStateFalse
isStateFalse: 10101
isStateTrue: What do you do?
 */
