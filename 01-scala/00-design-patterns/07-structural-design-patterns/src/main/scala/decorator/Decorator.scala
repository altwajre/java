package decorator

import java.io.{BufferedInputStream, BufferedReader, ByteArrayOutputStream, InputStreamReader}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.typesafe.scalalogging.LazyLogging

import scala.collection.JavaConverters._

/*
The decorator design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/d75750ba-2189-44e7-9992-65b66efb7e36.xhtml

The purpose of the decorator design pattern is to add functionality to objects without extending them and
without affecting the behavior of other objects from the same class.

 */

object Decorator {
  trait InputReader {
    def readLines(): Stream[String]
  }
  // Original class
  class AdvancedInputReader(reader: BufferedReader) extends InputReader {
    override def readLines(): Stream[String] = reader.lines().iterator().asScala.toStream
  }

  // Decorators
  abstract class InputReaderDecorator(inputReader: InputReader) extends InputReader {
    override def readLines(): Stream[String] = inputReader.readLines()
  }

  class CapitalizedInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {
    override def readLines(): Stream[String] = super.readLines().map(_.toUpperCase)
  }

  class CompressingInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) with LazyLogging {
    override def readLines(): Stream[String] = super.readLines().map {
      case line => {
        val text = line.getBytes(Charset.forName("UTF-8"))
        logger.info("Length before compression: {}", text.length.toString)
        val output = new ByteArrayOutputStream()
        val compressor = new GZIPOutputStream(output)
        try {
          compressor.write(text, 0, text.length)
          val outputByteArray = output.toByteArray
          logger.info("Length after compression: {}", outputByteArray.length.toString)
          new String(outputByteArray, Charset.forName("UTF-8"))
        } finally {
          compressor.close()
          output.close()
        }
      }
    }
  }

  class Base64EncoderInputReader(inputReader: InputReader) extends InputReaderDecorator(inputReader) {
    override def readLines(): Stream[String] = super.readLines().map {
      case line => Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8")))
    }
  }

  def main(args: Array[String]) = {
    useCapitalizedInputReaderDecorator()

    useMultipleDecorators()
  }

  def useCapitalizedInputReaderDecorator() = {
    println("# useCapitalizedInputReaderDecorator")
    val stream = new BufferedReader(new InputStreamReader(
      new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))
    ))
    try {
      val reader = new CapitalizedInputReader(new AdvancedInputReader(stream))
      reader.readLines().foreach(println)
    }
    finally {
      stream.close()
    }
  }

  def useMultipleDecorators() = {
    println("# useMultipleDecorators")
    val stream = new BufferedReader(
      new InputStreamReader(
        new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))
      )
    )
    try {
      val reader = new CompressingInputReader(
        new Base64EncoderInputReader(
          new CapitalizedInputReader(
            new AdvancedInputReader(stream)
          )
        )
      )
      reader.readLines().foreach(println)
    } finally {
      stream.close()
    }
  }
}
/*
# useCapitalizedInputReaderDecorator
THIS IS A DATA FILE
WHICH CONTAINS LINES
AND THOSE LINES WILL BE
MANIPULATED BY OUR STREAM READER.
# useMultipleDecorators
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
       
       
       
       
 */
