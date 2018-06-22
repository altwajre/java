package factories.factory_method

import factories.{SimpleConnection, SimpleMysqlConnection, SimplePgSqlConnection}

/*
The factory method design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/00d44705-3a50-4403-8fe2-b68fd52a8e56.xhtml
 */
abstract class DatabaseClient {
  def executeQuery(query: String) = {
    val connection = connect()
    connection.executeQuery(query)
  }

  // factory method connect()
  protected def connect(): SimpleConnection
}

// inheritance
class MySqlClient extends DatabaseClient {
  override protected def connect(): SimpleConnection = new SimpleMysqlConnection
}

class PgSqlClient extends DatabaseClient {
  override protected def connect(): SimpleConnection = new SimplePgSqlConnection
}

object FactoryMethod {
  def main(args: Array[String]) = {
    val clientMySql = new MySqlClient
    val clientPgSql = new PgSqlClient
    clientMySql.executeQuery("SELECT * FROM mysql")
    clientPgSql.executeQuery("SELECT * FROM pgsql")
  }
}
/*
Executing the query 'SELECT * FROM mysql' the MySQL way.
Executing the query 'SELECT * FROM pgsql' the PgSQL way.
 */
