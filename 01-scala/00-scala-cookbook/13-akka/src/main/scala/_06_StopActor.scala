import akka.actor.{Actor, ActorSystem, Kill, PoisonPill, Props}

import scala.concurrent.{Await, Future}
import akka.pattern.gracefulStop

import scala.concurrent.duration._

/*
Stopping Actors

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s07.html

- system.stop()
- send the actor a PoisonPill message
- program a gracefulStop
 */
object SystemStopTest {

  class TestActor extends Actor {
    override def receive: Receive = {
      case s: String => println("Message Received: " + s)
      case _ => println(s"unknown message")
    }
  }

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("SystemStop")
    val actor = actorSystem.actorOf(Props[TestActor], name = "actor")

    actor ! "hello"

    // stop our actor
    actorSystem.stop(actor)
    actorSystem.terminate
  }
}

/*
Message Received: hello
 */

object PoisonPillTest {

  class TestActor extends Actor {
    override def receive: Receive = {
      case s: String => println("Message Received: " + s)
      case _ => println(s"unknown message")
    }

    override def postStop(): Unit = {
      println("TestActor.postStop called")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("PoisonPillTest")
    val actor = system.actorOf(Props[TestActor], name = "actor")

    // a simple message
    actor ! "before PoisonPill"

    // the PoisonPill
    actor ! PoisonPill

    // these messages will not be processed
    actor ! "after PoisonPill"
    actor ! "hello?!"

    system.terminate
  }
}

/*
Message Received: before PoisonPill
TestActor.postStop called
[INFO] [08/03/2018 16:44:32.686] [PoisonPillTest-akka.actor.default-dispatcher-2] [akka://PoisonPillTest/user/actor] Message [java.lang.String] from Actor[akka://PoisonPillTest/deadLetters] to Actor[akka://PoisonPillTest/user/actor#1414370624] was not delivered. [1] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
[INFO] [08/03/2018 16:44:32.686] [PoisonPillTest-akka.actor.default-dispatcher-2] [akka://PoisonPillTest/user/actor] Message [java.lang.String] from Actor[akka://PoisonPillTest/deadLetters] to Actor[akka://PoisonPillTest/user/actor#1414370624] was not delivered. [2] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
 */

object GracefulStopTest {

  class TestActor extends Actor {
    override def receive: Receive = {
      case s: String => println("Message Received: " + s)
      case _ => println(s"unknown message")
    }

    override def postStop(): Unit = {
      println("TestActor.postStop called")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("GracefulStopTest")
    val actor = system.actorOf(Props[TestActor], name = "actor")

    // try to stop the actor gracefully
    try {
      val stopped: Future[Boolean] = gracefulStop(actor, 2 seconds)
      Await.result(stopped, 3 seconds)
      println("actor was stopped")
    }
    catch {
      case e: Exception => e.printStackTrace
    }
    finally {
      system.terminate
    }
  }
}

/*
TestActor.postStop called
actor was stopped
 */

object KillTest {

  class TestActor extends Actor {
    override def receive: Receive = {
      case s: String => println("Message Received: " + s)
      case _ => println(s"unknown message")
    }

    override def preStart {
      println("TestActor is alive")
    }

    override def postStop {
      println("TestActor::postStop called")
    }

    override def preRestart(reason: Throwable, message: Option[Any]) {
      println("TestActor::preRestart called")
    }

    override def postRestart(reason: Throwable) {
      println("TestActor::postRestart called")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("KillTestSystem")
    val actor = system.actorOf(Props[TestActor], name = "actor")
    actor ! "hello"

    // send the Kill message
    actor ! Kill
    system.terminate
  }
}

/*
TestActor is alive
Message Received: hello
TestActor::postStop called
 */
