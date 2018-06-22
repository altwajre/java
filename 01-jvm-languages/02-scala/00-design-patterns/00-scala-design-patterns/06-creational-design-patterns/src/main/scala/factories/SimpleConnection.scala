package factories

trait SimpleConnection {
  def getName(): String
  def executeQuery(query: String): Unit
}

class SimpleMysqlConnection extends SimpleConnection {
  override def getName(): String = {
    "SimpleMysqlConnection"
  }

  override def executeQuery(query: String): Unit = {
    println(s"Executing the query '$query' the MySQL way.")
  }
}

class SimplePgSqlConnection extends SimpleConnection {
  override def getName(): String = {
    "SimplePgSqlConnection"
  }

  override def executeQuery(query: String): Unit = {
    println(s"Executing the query '$query' the PgSQL way.")
  }
}
