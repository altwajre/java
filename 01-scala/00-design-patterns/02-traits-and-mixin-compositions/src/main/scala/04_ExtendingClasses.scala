/*
Extending classes

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/33c045e2-4d35-411c-99ad-ad9e92aac8c8.xhtml

PgSqlConnector need to implement the abstract class methods.
 */
abstract class Connector {
  def connect()
  def close()
}
trait ConnectorWithHelper extends Connector {
  def findDriver(): Unit = {
    println("Find driver called.")
  }
}

class PgSqlConnector extends ConnectorWithHelper {
  override def connect(): Unit = {
    println("Connected")
  }

  override def close(): Unit = {
    println("Closed")
  }
}

object ExtendingClasses {
  def main(args: Array[String]): Unit = {
    val pgSqlConnector = new PgSqlConnector()
    pgSqlConnector.findDriver()
    pgSqlConnector.connect()
    pgSqlConnector.close()
  }
}
/*
Find driver called.
Connected
Closed
 */
