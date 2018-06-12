import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

/*
Configurating Akka applications

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/25df43a8-20b7-4cd4-8b0e-80e0488c84f6.xhtml

There are many properties that need to be set in the Akka application, either programmatically or via configuration.
Configuration is the recommended approach to set the properties.

Config file
main/resources/akka.conf

https://doc.akka.io/docs/akka/snapshot/general/configuration.html
 */

class MyActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(msg)
  }
}

object ActorWithConfig extends App {
  private val config: Config = ConfigFactory.load("akka.conf")
  private val system = ActorSystem(config.getString("myactor.actorsystem"))
  private val actor: ActorRef = system.actorOf(Props[MyActor], config.getString("myactor.actorname"))
  println(actor.path)
  system.terminate()
}
/*
akka://actorconfigsystem/user/actor1
 */
