import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, FlowShape}
import akka.stream.scaladsl.{Balance, Flow, GraphDSL, Merge, Sink, Source}

import scala.util.Random

/*
Pipelining and parallelizing streams

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/1550f73e-2f09-4f42-970b-87bd9cf3e953.xhtml

Akka Streams provides a set of high-level APIs to process streams using actors as the underlying technology.
ActorMaterializer is responsible for making this happen. ActorMaterializer allocates the resources and instantiates
the classes required to have to turn your defined stream into a RunnableGraph.

By default, all the processing stages are combined and executed sequentially.

Some use cases could benefit from parallelizing some processing tasks. Akka Streams provides a method called async
to indicate when a stage should run asynchronously and have its own internal actor.

By default, all the stages not marked async will run in a single actor.



 */

trait PipeliningParallelizing extends App {
  implicit val actorSystem = ActorSystem("PipeliningParallelizing")
  implicit val actorMaterializer = ActorMaterializer()

  case class Wash(id: Int)

  case class Dry(id: Int)

  case class Done(id: Int)

  val tasks = (1 to 5).map(Wash)

  def washStage = Flow[Wash].map(wash => {
    val sleepTime = Random.nextInt(3) * 1000
    println(s"Washing ${wash.id}. It will take $sleepTime milliseconds.")
    Thread.sleep(sleepTime)
    Dry(wash.id)
  })

  def dryStage = Flow[Dry].map(dry => {
    val sleepTime = Random.nextInt(3) * 1000
    println(s"Drying ${dry.id}. It will take $sleepTime milliseconds.")
    Thread.sleep(sleepTime)
    Done(dry.id)
  })

  val parallelStage = Flow.fromGraph(

    GraphDSL.create() { implicit builder =>
      import GraphDSL.Implicits._

      val dispatchLaundry = builder.add(Balance[Wash](3))
      val mergeLaundry = builder.add(Merge[Done](3))

      dispatchLaundry.out(0) ~> washStage.async ~>
        dryStage.async ~> mergeLaundry.in(0)
      dispatchLaundry.out(1) ~> washStage.async ~>
        dryStage.async ~> mergeLaundry.in(1)
      dispatchLaundry.out(2) ~> washStage.async ~>
        dryStage.async ~> mergeLaundry.in(2)

      FlowShape(dispatchLaundry.in, mergeLaundry.out)
    }
  )

  def runGraph(testingFlow: Flow[Wash, Done, NotUsed]) =
    Source(tasks).via(testingFlow).to(Sink.foreach(println)).run()
}

object SynchronousPipeliningApplication extends PipeliningParallelizing {
  runGraph(Flow[Wash].via(washStage).via(dryStage))
}
/*
Washing 1. It will take 0 milliseconds.
Drying 1. It will take 1000 milliseconds.
Done(1)
Washing 2. It will take 1000 milliseconds.
Drying 2. It will take 1000 milliseconds.
Done(2)
Washing 3. It will take 2000 milliseconds.
Drying 3. It will take 0 milliseconds.
Done(3)
Washing 4. It will take 2000 milliseconds.
Drying 4. It will take 0 milliseconds.
Done(4)
Washing 5. It will take 0 milliseconds.
Drying 5. It will take 2000 milliseconds.
Done(5)
 */

object AsynchronousPipeliningApplication extends PipeliningParallelizing {
  runGraph(Flow[Wash].via(washStage.async).via(dryStage.async))
}
/*
Washing 1. It will take 0 milliseconds.
Washing 2. It will take 1000 milliseconds.
Drying 1. It will take 2000 milliseconds.
Washing 3. It will take 1000 milliseconds.
Washing 4. It will take 2000 milliseconds.
Drying 2. It will take 1000 milliseconds.
Done(1)
Drying 3. It will take 0 milliseconds.
Done(2)
Done(3)
Washing 5. It will take 0 milliseconds.
Drying 4. It will take 2000 milliseconds.
Drying 5. It will take 1000 milliseconds.
Done(4)
Done(5)
 */

object ParallelizingApplication extends PipeliningParallelizing {
  runGraph(Flow[Wash].via(parallelStage))
}
/*
Washing 1. It will take 2000 milliseconds.
Washing 2. It will take 1000 milliseconds.
Drying 1. It will take 0 milliseconds.
Done(1)
Washing 3. It will take 2000 milliseconds.
Drying 2. It will take 2000 milliseconds.
Washing 4. It will take 2000 milliseconds.
Done(2)
Drying 3. It will take 0 milliseconds.
Done(3)
Washing 5. It will take 0 milliseconds.
Drying 4. It will take 2000 milliseconds.
Drying 5. It will take 1000 milliseconds.
Done(4)
Done(5)
 */
