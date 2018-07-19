package template

import java.io.{ByteArrayInputStream, InputStreamReader}

import com.github.tototoshi.csv.CSVReader
import org.json4s.{DefaultFormats, StringInput}
import org.json4s.jackson.JsonMethods

/*
The template method design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0f021760-449c-46ce-bb23-d172c87256e9.xhtml

The purpose of the template method design pattern is to defer algorithm steps to subclasses using template methods.
 */
object Template {

  // Model
  case class Person(name: String, age: Int, address: String)

  // Template method
  abstract class DataFinder[T, Y] {

    def find(f: T => Option[Y]): Option[Y] =
      try {
        val data = readData()
        val parsed = parse(data)
        f(parsed)
      } finally {
        cleanup()
      }

    def readData(): Array[Byte]

    def parse(data: Array[Byte]): T

    def cleanup()
  }

  class JsonDataFinder extends DataFinder[List[Person], Person] {
    implicit val formats = DefaultFormats

    override def readData(): Array[Byte] = {
      val stream = this.getClass.getResourceAsStream("people.json")
      Stream.continually(stream.read).takeWhile(_ != -1).map(_.toByte).toArray
    }

    override def cleanup(): Unit = {
      System.out.println("Reading json: nothing to do.")
    }

    override def parse(data: Array[Byte]): List[Person] =
      JsonMethods.parse(StringInput(new String(data, "UTF-8"))).extract[List[Person]]
  }

  class CSVDataFinder extends DataFinder[List[Person], Person] {
    override def readData(): Array[Byte] = {
      val stream = this.getClass.getResourceAsStream("people.csv")
      Stream.continually(stream.read).takeWhile(_ != -1).map(_.toByte).toArray
    }

    override def cleanup(): Unit = {
      System.out.println("Reading csv: nothing to do.")
    }

    override def parse(data: Array[Byte]): List[Person] =
      CSVReader.open(new InputStreamReader(new ByteArrayInputStream(data))).all().map {
        case List(name, age, address) =>
          Person(name, age.toInt, address)
      }
  }

  def main(args: Array[String]) = {
    val jsonDataFinder = new JsonDataFinder
    val csvDataFinder = new CSVDataFinder

    println(s"Find a person with name Ivan in the json: ${jsonDataFinder.find(_.find(_.name == "Ivan"))}")
    println(s"Find a person with name James in the json: ${jsonDataFinder.find(_.find(_.name == "James"))}")

    println(s"Find a person with name Maria in the csv: ${csvDataFinder.find(_.find(_.name == "Maria"))}")
    println(s"Find a person with name Alice in the csv: ${csvDataFinder.find(_.find(_.name == "Alice"))}")
  }
}
/*
Reading json: nothing to do.
Find a person with name Ivan in the json: Some(Person(Ivan,26,London))
Reading json: nothing to do.
Find a person with name James in the json: None
Reading csv: nothing to do.
Find a person with name Maria in the csv: Some(Person(Maria,23,Edinburgh))
Reading csv: nothing to do.
Find a person with name Alice in the csv: None
 */
