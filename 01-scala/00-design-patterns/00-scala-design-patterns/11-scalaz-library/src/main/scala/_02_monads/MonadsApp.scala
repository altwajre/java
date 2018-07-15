package _02_monads

import java.io.{File, PrintWriter}

import scalaz._
import effect._
import Scalaz._
import IO._

import scala.io.Source

/*
Monads in Scalaz

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/746aa3ac-355c-4a2d-8008-d702adfd3451.xhtml

- Functors: map; listFunctor.map(numbers)(_ * 2)
- Monads: flatMap: Seq("abc", "xyz").flatMap(_.toUpperCase())

Mote:
.pure[IO] passes people var to monad. the passed value is lazily evaluated.
The putStrLn and readLn methods also return an instance of the IO monad and they are just helpers.
To trigger the actual actions, we must call unsafePerformIO on an IO instance.
The IO monad helps us to build a computation and execute it at the last moment.

 */
object MonadsApp {

  object monads {
    def readFile(path: String) = {
      System.out.println(s"Reading file ${path}")
      Source.fromFile(path).getLines()
    }

    def writeFile(path: String, lines: Iterator[String]) = {
      System.out.println(s"Writing file ${path}")
      val file = new File(path)
      printToFile(file) { p => lines.foreach(p.println) }
    }

    private def printToFile(file: File)(writeOp: PrintWriter => Unit): Unit = {
      val writer = new PrintWriter(file)
      try {
        writeOp(writer)
      } finally {
        writer.close()
      }
    }
  }

  case class Person(name: String, age: Int)

  object Person {
    def fromArray(arr: Array[String]): Option[Person] =
      arr match {
        case Array(name, age) => Some(Person(name, age.toInt))
        case _ => None
      }
  }

  import monads._

  def main(args: Array[String]): Unit = {
    val localArgs = Array("people.tsv", "true")
    localArgs match {
      case Array(inputFile, isWriteToFile) =>
//        val people = {
//          for {
//            line <- readFile(inputFile)
//            person <- Person.fromArray(line.split("\t"))
//          } yield person
//        }.pure[IO] // .pure[IO] passes people var to monad. the passed value is lazily evaluated.

        // the more monadical version
        val people = for {
          lines <- readFile(inputFile).pure[IO]
          p <- lines.map(i => Person("a", 1)).pure[IO]
        } yield p

        System.out.println("Still haven't done any IO!")
        System.out.println("About to do some...")
        if (isWriteToFile.toBoolean) {
          val writePeople = for {
            _ <- putStrLn("Read people successfully. Where to write them down?")
            outputFile <- readLn
            p <- people
            _ <- writeFile(outputFile, p.map(_.toString)).pure[IO]
          } yield ()
          System.out.println("Writing to file using toString.")
          writePeople.unsafePerformIO
        } else {
          System.out.println(s"Just got the following people: ${people.unsafePerformIO.toList}")
        }
      case _ =>
        System.err.println("Please provide input file and true/false whether to write to file.")
        System.exit(-1)
    }
  }

}
/*
$ when val localArgs = Array("people.tsv", "false")
Still haven't done any IO!
About to do some...
Reading file people.tsv
Just got the following people: List(Person(Ivan,26), Person(Maria,26), Person(John,25))

$ when val localArgs = Array("people.tsv", "true")
Still haven't done any IO!
About to do some...
Writing to file using toString.
Read people successfully. Where to write them down?
people.out
Reading file people.tsv
Writing file people.out
 */