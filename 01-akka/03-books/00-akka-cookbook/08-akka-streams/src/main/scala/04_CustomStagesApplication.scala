import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.stream.stage._

import scala.concurrent.duration._

/*
Custom stream processing

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/9575406a-6a3b-4b0a-974f-2e95b0fc67a3.xhtml

Akka Streams provides a set of APIs to create your own custom stage.
Since Streams are essentially processing graphs, to create a custom stage, you need to extend the GraphStage abstraction.

The main difference between GraphDSL, which is used to compose multiple stages into a single stage,
and GraphStage is that the latter cannot be decomposed into smaller pieces.

At this point, we need to learn a bit about how Akka Streams works internally.
To begin with, there is the concept of shape. A shape defines the number of input and output ports in your stage (known as inlets and outlets).
For example, SourceShape has only one outlet. A SinkShape has one inlet. A FlowShape has one inlet and one outlet.
It is also possible to have an AmorphousShape, which has an arbitrary number of inlets and outlets.
Every GraphStage also encompasses stage logic. This logic defines a set of handlers (InHandler or OutHandler) that implement the behavior of the different ports.
All of the logic should extend GraphStageLogic and can be stateful or stateless, depending on your use case.

These handlers are also part of the back-pressure mechanism. We will demonstrate how to use them in this recipe.

How it works...

we implemented two custom stages. First, we created a custom Source stage. Since a Source emits elements into a stream, we implemented GraphStageLogic with an OutHandler.
This handler provides the onPull method that will be called by the downstream stages when there is demand for new elements. In this example, we pushed a string.

Secondly, we created a custom Sink stage. This time, we implemented
TimerGraphStageLogic with an InHandler since a Sink receives elements from a stream. TimerGraphStageLogic provides APIs to schedule messages within the stage.
To request for more elements from the upstream, we used the pull and grab methods.
These methods help consume elements from the stream and manage back pressure.
Pull requests an element from the stream. Calling this method twice before an element has arrived will make it throw an exception.
The grab method actually returns the element that comes from the stream. Therefore, it is required to call pull, and then grab to receive an element.
In addition, we scheduled a None timer key to be periodically sent to the stage every 5 seconds. Every time the scheduler is triggered, the onTimer method is called with the timer key.
 */

// Define a SourceShape emitting String elements
class HelloAkkaStreamSource extends GraphStage[SourceShape[String]] {
  val out: Outlet[String] = Outlet("SystemInputSource")
  override val shape: SourceShape[String] = SourceShape(out)

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) {
    setHandler(out, new OutHandler {
      override def onPull(): Unit = {
        val line = "Hello World Akka Streams!"
        push(out, line)
      }
    })
  }
}

// Receive elements from a stream
class WordCounterSink extends GraphStage[SinkShape[String]] {
  val in: Inlet[String] = Inlet("WordCounterSink")
  override val shape: SinkShape[String] = SinkShape(in)

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new TimerGraphStageLogic(shape) {
    private var counts: Map[String, Int] = Map.empty[String, Int].withDefaultValue(0)

    override def preStart(): Unit = {
      println("preStart")
      schedulePeriodically(None, 1 seconds)
      pull(in)
    }

    setHandler(in, new InHandler {
      override def onPush(): Unit = {
        val word = grab(in)
        counts += word -> (counts(word) + 1)
        // see the beginning counts
        if(counts.get("HELLO").get < 3){
          println(counts)
        }
        pull(in)
      }
    })

    override def onTimer(timerKey: Any) = {
      println(s"$timerKey: at ${System.currentTimeMillis()} count map is $counts")
    }
  }
}

object CustomStagesApplication extends App {
  implicit val actorSystem = ActorSystem("CustomStages")
  implicit val actorMaterializer = ActorMaterializer()

  val source = Source.fromGraph(new HelloAkkaStreamSource())
  val upperCaseMapper = Flow[String].map(_.toUpperCase())
  val splitter = Flow[String].mapConcat(_.split(" ").toList)
  val punctuationMapper = Flow[String].map(_.replaceAll("""[p{Punct}&&[^.]]""", "").replaceAll(System.lineSeparator(), ""))
  val filterEmptyElements = Flow[String].filter(_.nonEmpty)
  val wordCounterSink = Sink.fromGraph(new WordCounterSink())

  val stream = source
    .via(upperCaseMapper)
    .via(splitter)
    .via(punctuationMapper)
    .via(filterEmptyElements)
    .to(wordCounterSink)

  stream.run()
}
/*
preStart
Map(HELLO -> 1)
Map(HELLO -> 1, WORLD -> 1)
Map(HELLO -> 1, WORLD -> 1, AKKA -> 1)
Map(HELLO -> 1, WORLD -> 1, AKKA -> 1, STREAMS! -> 1)
Map(HELLO -> 2, WORLD -> 1, AKKA -> 1, STREAMS! -> 1)
Map(HELLO -> 2, WORLD -> 2, AKKA -> 1, STREAMS! -> 1)
Map(HELLO -> 2, WORLD -> 2, AKKA -> 2, STREAMS! -> 1)
Map(HELLO -> 2, WORLD -> 2, AKKA -> 2, STREAMS! -> 2)
None: at 1528932835022 count map is Map(HELLO -> 207378, WORLD -> 207378, AKKA -> 207378, STREAMS! -> 207378)
None: at 1528932836020 count map is Map(HELLO -> 499134, WORLD -> 499134, AKKA -> 499134, STREAMS! -> 499133)
None: at 1528932837021 count map is Map(HELLO -> 698148, WORLD -> 698147, AKKA -> 698147, STREAMS! -> 698147)
None: at 1528932838021 count map is Map(HELLO -> 1051158, WORLD -> 1051157, AKKA -> 1051157, STREAMS! -> 1051157)
None: at 1528932839022 count map is Map(HELLO -> 1415729, WORLD -> 1415729, AKKA -> 1415729, STREAMS! -> 1415729)
 */
