package com.company.app

import java.util.concurrent.ConcurrentLinkedQueue

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.{Envelope, MailboxType, MessageQueue, ProducesMessageQueue}
import com.typesafe.config.Config

class MyMessageQueue extends MessageQueue {
  private final val queue = new ConcurrentLinkedQueue[Envelope]()

  override def enqueue(receiver: ActorRef, handle: Envelope): Unit = {
    // enqueue when it is MyActor
    if(handle.sender.path.name == "MyActor") {
      handle.sender ! "Hey dude, How are you? I know your name, processing your request"
      queue.offer(handle) // enqueue
    }
      // ignore the message
    else {
      handle.sender ! "I don't talk to strangers. I can't process your request"
    }
  }

  override def dequeue(): Envelope = queue.poll

  override def numberOfMessages: Int = queue.size

  override def hasMessages: Boolean = !queue.isEmpty

  override def cleanUp(owner: ActorRef, deadLetters: MessageQueue): Unit = {
    while (hasMessages) {
      deadLetters.enqueue(owner, dequeue())
    }
  }
}

class MyUnboundedMailbox extends MailboxType with ProducesMessageQueue[MyMessageQueue] {
  // This constructor signature must exist, it will be called by Akka
  def this(settings: ActorSystem.Settings, config: Config) = {
    // put your initialization code here
    this()
  }
  override def create(owner: Option[ActorRef], system: Option[ActorSystem]): MessageQueue = new MyMessageQueue()
}

// MyActor tries to communicate with MySpecialActor
// MyActor, actor1 and actor2 send messages to MySpecialActor
class MySpecialActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"msg is $msg")
  }
}
class MyActor extends Actor {
  override def receive: Receive = {
    case (msg: String, actorRef: ActorRef) => actorRef ! msg
    case msg => println(msg)
  }
}

object CustomMailbox extends App {
  private val system = ActorSystem("CustomMailbox")
  private val actor: ActorRef = system.actorOf(Props[MySpecialActor].withDispatcher("custom-dispatcher"))
  private val actor1: ActorRef = system.actorOf(Props[MyActor], "stranger")
  private val actor2: ActorRef = system.actorOf(Props[MyActor], "MyActor")

  actor1 ! ("hello", actor)
  actor2 ! ("hello", actor)

  system.terminate()
}
/*
Hey dude, How are you? I know your name, processing your request
I don't talk to strangers. I can't process your request
msg is hello
 */

