import java.nio.file.{Path, Paths}

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, IOResult}
import akka.stream.scaladsl.{Compression, FileIO, RunnableGraph, Sink}

import scala.concurrent.Future

case object Foo2
/*
How to transform streams and consume them

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/ae606738-3f95-4392-8a52-57df6ff15240.xhtml

A Source is responsible for consuming elements and emitting them through the stream.
A Flow is responsible for transforming elements as they go. In this recipe, we will revisit the out-of-the-box transforming functions a Flow provides.

 */
object TransformingStreamsApplication extends App {
  implicit val actorSystem = ActorSystem("TransformingStreams")
  implicit val actorMaterializer = ActorMaterializer()
  val MaxGroups = 100

  private val path: Path = Paths.get("src/main/resources/gzipped-file.gz")

  private val stream: RunnableGraph[Future[IOResult]] = FileIO.fromPath(path)
    .via(Compression.gunzip())
    .map(_.utf8String.toUpperCase)
    .mapConcat(_.split(" ").toList)
    .collect {
      case w if w.nonEmpty => {
        w.replaceAll("""[p{Punct}&&[^.]]""", "").replaceAll(System.lineSeparator(), "")
      }
    }
    .groupBy(MaxGroups, identity)
    .map(_ -> 1)
    .reduce((l, r) => (l._1, l._2 + r._2))
    .mergeSubstreams
    .to(Sink.foreach(println))

  stream.run()
}
/*
(NO,1)
(HELLO,2)
(SERIOUSLY,,1)
(FROM,2)
(STREAMS!,1)
(WORLD,2)
(AKKA,2)
(STREAMS!!!,1)
 */
