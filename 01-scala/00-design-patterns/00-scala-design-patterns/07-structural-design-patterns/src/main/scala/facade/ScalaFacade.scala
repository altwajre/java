package facade

import java.util.Base64

import com.typesafe.scalalogging.LazyLogging
import org.json4s.{DefaultFormats, StringInput}
import org.json4s.jackson.JsonMethods

/*
The facade design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0c5a4141-ea27-4e06-bd51-4a33adeaee68.xhtml

Facade makes things simpler
 */
object ScalaFacade {
  case class Person(name: String, age: Int)

  trait DataDownloader extends LazyLogging {
    def download(url: String): Array[Byte] = {
      logger.info("Downloading from: {}", url)
      Thread.sleep(1000)
      //    {
      //      "name": "Ivan",
      //      "age": 26
      //    }
      //    the string below is the Base64 encoded Json above.
      "ew0KICAgICJuYW1lIjogIkl2YW4iLA0KICAgICJhZ2UiOiAyNg0KfQ==".getBytes
    }
  }

  trait DataDecoder {
    def decode(data: Array[Byte]): String =
      new String(Base64.getDecoder.decode(data), "UTF-8")
  }

  trait DataDeserializer {
    implicit val formats = DefaultFormats

    def parse[T](data: String)(implicit m: Manifest[T]): T =
      JsonMethods.parse(StringInput(data)).extract[T]
  }

  // Facade makes implementations above simple to use
  class DataReader extends DataDownloader with DataDecoder with DataDeserializer {
    def readPerson(url: String): Person = {
      val data = download(url)
      val json = decode(data)
      parse[Person](json)
    }
  }

  def main(args: Array[String]) = {
    val reader = new DataReader
    println(s"Just read the following person: ${reader.readPerson("https://www.ivan-nikolov.com/")}")
  }
}
/*
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Just read the following person: Person(Ivan,26)
 */
