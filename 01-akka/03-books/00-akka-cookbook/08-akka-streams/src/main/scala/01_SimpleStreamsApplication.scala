import java.io.File

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{RunnableGraph, Sink, Source}

case object Foo

/*
Creating simple Akka Streams

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/35c08e4a-a285-4c3d-89ac-53b6bc54b70a.xhtml

A stream is a set of components with different responsibilities. Some components consume data, some transform data, and others deliver data.
Akka Streams uses these components as modular pieces that you put together to create a runnable stream.
These components are reusable, which allows us to use the don't repeat yourself (DRY) principle.

Each stream topology basically consists of the following:

- Source: This is the entry point to your stream. There must be at least one in every stream.
- Sink: This is the exit point of your stream. There must be at least one in every stream.
- Flow: This is the component responsible for manipulating the elements of the stream. You can have none or any number of them.

These components are also known as stages. There are multiple built-in stages that allow you to do the most common operations.
These operations range from mapping. filtering, and merging to more complicated functions, such as statefulMapConcat.
We will review most of these out-of-the-box stages in the Working with Graphs recipe.
To get started with Akka Streams, we are going to create a simple application that will check some files.

 */
object SimpleStreamsApplication extends App {
  implicit val system = ActorSystem("SimpleStreams")
  implicit val actorMaterializer = ActorMaterializer()

  private val fileList = List("src/main/resources/testfile1.txt",
    "src/main/resources/testfile2.txt",
    "src/main/resources/testfile3.txt")

  private val stream: RunnableGraph[NotUsed] = Source(fileList)
    .map(new File(_))
    .filter(_.exists())
    .filter(_.length() != 0)
    .to(Sink.foreach(f => println(s"Absolute path: ${f.getAbsolutePath}")))

  stream.run()

}
/*
Absolute path: /Users/whan/git/personal/java/01-akka/03-books/00-akka-cookbook/08-akka-streams/src/main/resources/testfile3.txt
 */
