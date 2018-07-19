package strategy

import java.io.InputStreamReader

import com.github.tototoshi.csv.CSVReader
import org.json4s.jackson.JsonMethods
import org.json4s.{DefaultFormats, StreamInput}

/*
The strategy design pattern the Scala way

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/a771ccb6-221b-45bc-88a9-c29c45553923.xhtml

The strategy parameter is a function instread of a normal object.
This instantly allows us to pass any function we want there without the need to implement specific classes.
 */
object ScalaStrategy {
  // Model
  case class Person(name: String, age: Int, address: String)

  class Application[T](strategy: (String) => List[T]) {
    def write(file: String) = {
      println(s"Got the following data ${strategy(file)}")
    }
  }

  object StrategyFactory {
    implicit val formats = DefaultFormats
    def apply(filename: String): (String) => List[Person] =
      filename match {
        case f if f.endsWith(".json") => parseJson
        case f if f.endsWith(".csv") => parseCsv
        case f => throw new RuntimeException(s"Unknown format: $f")
      }

    def parseJson(file: String): List[Person] = {
      JsonMethods.parse(StreamInput(this.getClass.getResourceAsStream(file))).extract[List[Person]]
    }

    def parseCsv(file: String): List[Person] =
      CSVReader.open(new InputStreamReader(this.getClass.getResourceAsStream(file))).all().map {
        case List(name, age, address) =>
          Person(name, age.toInt, address)
      }

  }

  def main(args: Array[String]) = {
    val parseJsonFunc = StrategyFactory("people.csv")
    println(parseJsonFunc.getClass.getSimpleName)
    val applicationCsv = new Application[Person](parseJsonFunc)
    val applicationJson = new Application[Person](StrategyFactory("people.json"))

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
