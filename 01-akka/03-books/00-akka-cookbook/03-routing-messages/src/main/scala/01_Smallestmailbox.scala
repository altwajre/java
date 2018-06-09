import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.SmallestMailboxPool

/*
# Creating a SmallestMailboxPool of actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/890aa03a-c79f-47ce-a36c-a6ef70784161.xhtml

SmallestMailboxPool is better since we know that the new message will go to the actor who is least busy.

- How it works

We implement the SmallestMailboxPool of five actors. In the application, we sent five messages to the actor.
Each message goes to a different actor.
*/
class SmallestmailboxActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"I am ${self.path.name}. message: $msg")
    case _ => println(s"I don't understand the message")
  }
}

object SmallestmailboxApp extends App {
  private val system = ActorSystem("Smallestmailbox")
  private val router: ActorRef = system.actorOf(SmallestMailboxPool(5).props(Props[SmallestmailboxActor]))
  for(i <- 1 to 8) {
    router ! s"Hello $i"
  }

  system.terminate()
}
/*
I am $e. message: Hello 5
I am $d. message: Hello 4
I am $c. message: Hello 3
I am $a. message: Hello 1
I am $b. message: Hello 2
I am $a. message: Hello 6
I am $b. message: Hello 7
I am $c. message: Hello 8
 */
