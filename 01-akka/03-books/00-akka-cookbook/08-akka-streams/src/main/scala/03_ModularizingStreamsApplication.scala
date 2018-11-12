import java.nio.file.{Path, Paths}

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, IOResult}
import akka.stream.scaladsl.{Compression, FileIO, Flow, RunnableGraph, Sink, Source}
import akka.util.ByteString

import scala.concurrent.Future

case object Foo3

/*
Creating stream sources, flows, and sinks

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/06991c63-3ef4-4000-bd97-be2f785ff147.xhtml

Modularize and reuse Akka streams.

 */
object ModularizingStreamsApplication extends App {
  implicit val actorSystem = ActorSystem("ModularizingStreams")
  implicit val actorMaterializer = ActorMaterializer()

  val MaxGroups = 1000

  private val path: Path = Paths.get("src/main/resources/gzipped-file.gz")
  private val source: Source[ByteString, Future[IOResult]] = FileIO.fromPath(path)

  private val gunzip: Flow[ByteString, ByteString, NotUsed] = Flow[ByteString].via(Compression.gunzip())
  private val utf8UppercaseMapper: Flow[ByteString, String, NotUsed] = Flow[ByteString].map(_.utf8String.toUpperCase)
  private val utf8LowercaseMapper: Flow[ByteString, String, NotUsed] = Flow[ByteString].map(_.utf8String.toLowerCase)
  private val splitter: Flow[String, String, NotUsed] = Flow[String].mapConcat(_.split(" ").toList)
  private val punctuationMapper = Flow[String].map(_.replaceAll("""[p{Punct}&&[^.]]""", "").replaceAll(System.lineSeparator(), ""))
  private val filterEmptyElements: Flow[String, String, NotUsed] = Flow[String].filter(_.nonEmpty)
  val wordCountFlow = Flow[String]
    .groupBy(MaxGroups, identity)
    .map(_ -> 1)
    .reduce((l, r) => (l._1, l._2 + r._2))
    .mergeSubstreams

  private val printlnSink: Sink[Any, Future[Done]] = Sink.foreach(println)

  private val streamUppercase: RunnableGraph[Future[IOResult]] = source
    .via(gunzip)
    .via(utf8UppercaseMapper)
    .via(splitter)
    .via(punctuationMapper)
    .via(filterEmptyElements)
    .via(wordCountFlow)
    .to(printlnSink)

  private val streamLowercase: RunnableGraph[Future[IOResult]] = source
    .via(gunzip)
    .via(utf8LowercaseMapper)
    .via(splitter)
    .via(punctuationMapper)
    .via(filterEmptyElements)
    .via(wordCountFlow)
    .to(printlnSink)
  streamLowercase

  println("# Uppercase")
  streamUppercase.run()
  Thread.sleep(1000)
  println("# Lowercase")
  streamLowercase.run()

}
/*
# Uppercase
(NO,1)
(HELLO,2)
(SERIOUSLY,,1)
(FROM,2)
(STREAMS!,1)
(WORLD,2)
(AKKA,2)
(STREAMS!!!,1)
# Lowercase
(sreams!,1)
(world,2)
(sreams!!!,1)
(from,2)
(hello,2)
(seriosly,,1)
(akka,2)
(o,1)
 */
