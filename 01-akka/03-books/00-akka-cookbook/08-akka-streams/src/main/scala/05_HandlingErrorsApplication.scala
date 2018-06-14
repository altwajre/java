import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, RunnableGraph, Sink, Source}
import akka.stream.{ActorAttributes, ActorMaterializer, ActorMaterializerSettings, Supervision}

object Main extends App{
  println("hi")
  println("hello" (2))
}

/*
Error Handling in Akka streams

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/5ceb66ff-6c6f-4bc7-b645-9c1df09bb595.xhtml

Akka provides a set of supervision strategies to deal with errors that happen in actors.
Akka Streams is no different and provides a similar approach to deal with unexpected exceptions.
For instance, these exceptions might occur when an element in the stream is not the expected type.

How it works...

Stop: The stream is completed with a failure.

Resume: The element gets dropped and the stream keeps running and processing subsequent elements.

Restart: The element gets dropped and the stream keeps running; however, it restarts the stage beforehand.
For stateful stages, the state will be lost since the restart happens by creating a new instance of the stage.
 */

object HandlingErrorsApplication extends App {
  implicit val actorSystem = ActorSystem("HandlingErrors")
  val streamDecider: Supervision.Decider = {
    case e: IndexOutOfBoundsException => {
      println("Dropping element because of IndexOutOfBoundsException. Resuming.")
      Supervision.Resume
    }
    case _ => Supervision.Stop
  }

  val flowDecider: Supervision.Decider = {
    case e: IllegalArgumentException => {
      println("Dropping element because of IllegalArgumentException. Restarting.")
      Supervision.Restart
    }
    case _ => Supervision.Stop
  }

  private val actorMaterializerSettings: ActorMaterializerSettings = ActorMaterializerSettings(actorSystem)
    .withSupervisionStrategy(streamDecider)

  implicit val actorMaterializer = ActorMaterializer(actorMaterializerSettings)

  val words = List("12345", "Errors", "In", "Akka", "Streams", "")

  val flow = Flow[String].map(word => {
    if(word.length == 0) {
      throw new IllegalArgumentException("Empty words are not allowed")
    }
    word
  }).withAttributes(ActorAttributes.supervisionStrategy(flowDecider))

  private val stream: RunnableGraph[NotUsed] = Source(words)
    .via(flow)
    .map(array => {
      println(s"map ${array}")
      // get index 2 character
      array(2)
    })
    .to(Sink.foreach(e => {
      println(s"${e}")
    }))
  stream.run()
}
/*
map 12345
3
map Errors
r
map In
Dropping element because of IndexOutOfBoundsException. Resuming.
map Akka
k
map Streams
r
Dropping element because of IllegalArgumentException. Restarting.
 */
