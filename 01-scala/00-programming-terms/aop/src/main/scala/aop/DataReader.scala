package aop

import org.json4s._
import org.json4s.jackson.JsonMethods._

/*
Aspect-oriented programming

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/62ba2869-1887-4d24-89ee-d9f77ead311c.xhtml

Timing our application with AOP

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/665fc249-01bd-44a3-ae67-a2b7473d58e9.xhtml

We create another trait called LoggingDataReader, which extends from DataReader.
abstract override modifier notifies the compiler that we will be doing stackable modifications.
 */
case class Person(firstName: String, lastName: String, age: Int)

trait DataReader {
  def readData(): List[Person]

  def readDataInefficiently(): List[Person]
}

class DataReaderImpl extends DataReader {
  implicit val formats = DefaultFormats

  private def readUntimed(): List[Person] = {
    parse(StreamInput(getClass.getResourceAsStream("/users.json"))).extract[List[Person]]
  }

  override def readData(): List[Person] = {
    readUntimed()
  }

  override def readDataInefficiently(): List[Person] = {
    (1 to 10000).foreach {
      case num => {
        readUntimed()
      }
    }
    readUntimed()
  }
}

// use stackable modifications to add more stuff
trait LoggingDataReader extends DataReader {
  // stackable modification
  abstract override def readData(): List[Person] = {
    val startMills = System.currentTimeMillis()
    val result = super.readData()
    val time = System.currentTimeMillis() - startMills
    println(s"readData took ${time} milliseconds")
    result
  }

  abstract override def readDataInefficiently(): List[Person] = {
    var startMills = System.currentTimeMillis()
    val result = super.readDataInefficiently()
    val time = System.currentTimeMillis() - startMills
    println(s"readDataInefficiently took ${time} milliseconds.")
    result
  }
}

object DataReaderAOP {
  def main(args: Array[String]) = {
    val dataReader = new DataReaderImpl with LoggingDataReader
    println(s"read data efficiently: ${dataReader.readData()}")
    println(s"read data inefficiently: ${dataReader.readDataInefficiently()}")
  }
}
/*
readData took 607 milliseconds
read data efficiently: List(Person(Tom,Niko,28), Person(John,Smith,18), Person(Harry,Cooper,38))
readDataInefficiently took 1321 milliseconds.
read data inefficiently: List(Person(Tom,Niko,28), Person(John,Smith,18), Person(Harry,Cooper,38))
 */

/*
// Uncomment for using the timing without AOP.

override def readData(): List[Person] = {
  val startMillis = System.currentTimeMillis()
  val result = readUntimed()
  val time = System.currentTimeMillis() - startMillis
  System.err.println(s"readData took ${time} milliseconds.")
  result
}

override def readDataInefficiently(): List[Person] = {
  val startMillis = System.currentTimeMillis()
  (1 to 10000).foreach {
    case num =>
      readUntimed()
  }
  val result = readUntimed()
  val time = System.currentTimeMillis() - startMillis
  System.err.println(s"readDataInefficiently took ${time} milliseconds.")
  result
}

object DataReaderApp {
  def main(args: Array[String]) = {
    val dataReader = new DataReaderImpl
    println(s"read data efficiently: ${dataReader.readData()}")
    println(s"read data inefficiently: ${dataReader.readDataInefficiently()}")
  }
}

*/

