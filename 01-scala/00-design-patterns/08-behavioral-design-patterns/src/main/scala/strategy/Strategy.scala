package strategy

import java.io.InputStreamReader

import com.github.tototoshi.csv.CSVReader
import org.json4s.{DefaultFormats, StreamInput}
import org.json4s.jackson.JsonMethods

/*
The strategy design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/3884d90b-5311-4ce2-9c8c-4dacbd665b50.xhtml

 */
object Strategy {
  // Model
  case class Person(name: String, age: Int, address: String)

  trait Parser[T] {
    def parse(file: String): List[T]
  }

  class CSVParser extends Parser[Person] {
    override def parse(file: String): List[Person] =
      CSVReader.open(new InputStreamReader(this.getClass.getResourceAsStream(file))).all().map {
        case List(name, age, address) =>
          Person(name, age.toInt, address)
      }
  }

  class JsonParser extends Parser[Person] {
    implicit val formats = DefaultFormats

    override def parse(file: String): List[Person] =
      JsonMethods.parse(StreamInput(this.getClass.getResourceAsStream(file))).extract[List[Person]]
  }

  object Parser {
    def apply(filename: String): Parser[Person] = {
      filename match {
        case f if f.endsWith(".json") => new JsonParser
        case f if f.endsWith(".csv") => new CSVParser
        case f => throw new RuntimeException(s"Unknown format: $f")
      }
    }
  }

  class PersonApplication[T](parser: Parser[T]) {
    def write(file: String): Unit = {
      println(s"Got the following data ${parser.parse(file)}")
    }
  }

  def main(args: Array[String]) = {
    val csvPeople = Parser("people.csv")
    val jsonPeople = Parser("people.json")
    val applicationCsv = new PersonApplication(csvPeople)
    val applicationJson = new PersonApplication(jsonPeople)
    println("# CSV")
    applicationCsv.write("people.csv")
    println("# JSON")
    applicationJson.write("people.json")
  }
}
/*
# CSV
Got the following data List(Person(Ivan,26,London), Person(Maria,23,Edinburgh), Person(John,36,New York), Person(Anna,24,Moscow))
# JSON
Got the following data List(Person(Ivan,26,London), Person(Maria,23,Edinburgh), Person(John,36,New York), Person(Anna,24,Moscow))
 */
