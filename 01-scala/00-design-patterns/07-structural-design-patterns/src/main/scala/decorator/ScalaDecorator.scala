package decorator

import java.io.{BufferedInputStream, BufferedReader, ByteArrayOutputStream, InputStreamReader}
import java.nio.charset.Charset
import java.util.Base64
import java.util.zip.GZIPOutputStream

import com.typesafe.scalalogging.LazyLogging

import scala.collection.JavaConverters._

/*
The decorator design pattern the Scala way

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/6ec1b94d-cbdc-4b8c-b942-20dfe9070d38.xhtml

Stackable traits follow the rules of linearization in a reverse order
 */
object ScalaDecorator {
  trait InputReader {
    def readLines(): Stream[String]
  }
  // Original class
  class AdvancedInputReader(reader: BufferedReader) extends InputReader {
    override def readLines(): Stream[String] = reader.lines().iterator().asScala.toStream
  }

  // Decorators - use trait to add functionality
  trait CapitalizedInputReaderTrait extends InputReader {
    abstract override def readLines(): Stream[String] = super.readLines().map(_.toUpperCase)
  }

  trait CompressingInputReaderTrait extends InputReader with LazyLogging {
    abstract override def readLines(): Stream[String] = super.readLines().map {
      case line =>
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

  trait Base64EncoderInputReaderTrait extends InputReader {
    abstract override def readLines(): Stream[String] = super.readLines().map {
      case line => Base64.getEncoder.encodeToString(line.getBytes(Charset.forName("UTF-8")))
    }
  }

  def main(args: Array[String]) = {
    useCapitalizedInputReaderDecorator()

    useMultipleDecorators()

  }

  def useCapitalizedInputReaderDecorator() = {
    println("# useCapitalizedInputReaderDecorator")
    val stream = new BufferedReader(
      new InputStreamReader(
        new BufferedInputStream(this.getClass.getResourceAsStream("data.txt"))
      )
    )
    try {
      val reader = new AdvancedInputReader(stream) with CapitalizedInputReaderTrait
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
      val reader = new AdvancedInputReader(stream) with CapitalizedInputReaderTrait with Base64EncoderInputReaderTrait with CompressingInputReaderTrait
      reader.readLines().foreach(println)
    }
    finally {
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
