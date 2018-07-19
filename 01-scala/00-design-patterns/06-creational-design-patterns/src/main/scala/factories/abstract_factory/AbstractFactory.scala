package factories.abstract_factory

import factories.{SimpleConnection, SimpleMysqlConnection, SimplePgSqlConnection}

/*
The abstract factory

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/134646a4-722a-410f-872e-faddacf65de8.xhtml

The abstract factory design relies on object composition in contrast to inheritance.
 */
trait DatabaseConnectorFactory {
  def createConnect(): SimpleConnection
}

class MySqlFactory extends DatabaseConnectorFactory {
  override def createConnect(): SimpleConnection = {
    new SimpleMysqlConnection
  }
}

// composition by factory (dependency injection)
class PgSqlFactory extends DatabaseConnectorFactory {
  override def createConnect(): SimpleConnection = {
    new SimplePgSqlConnection
  }
}

class DatabaseClient(connectorFactory: DatabaseConnectorFactory) {
  def executeQuery(query: String) = {
    val connection = connectorFactory.createConnect()
    connection.executeQuery(query)
  }
}

object AbstractFactory {
  def main(args: Array[String]) = {
    val clientMySql = new DatabaseClient(new MySqlFactory)
    val clientPgSql = new DatabaseClient(new PgSqlFactory)
    clientMySql.executeQuery("SELECT * FROM mysql")
    clientPgSql.executeQuery("SELECT * FROM pgsql")
  }
}
/*
Executing the query 'SELECT * FROM mysql' the MySQL way.
Executing the query 'SELECT * FROM pgsql' the PgSQL way.
 */
