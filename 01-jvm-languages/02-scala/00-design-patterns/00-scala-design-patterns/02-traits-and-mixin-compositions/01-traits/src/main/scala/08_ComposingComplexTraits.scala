/*
Composing complex traits

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/b206ae35-1326-4656-b068-2fe8f0711d78.xhtml

The error message tells us that since the ConnectorWithHelper trait extends the Connector class,
all the classes that use this trait for composition must be subclasses of Connector.
 */

//object ReallyExpensiveWatchUser {
//  def main(args: Array[String]): Unit = {
//    val reallyExpensiveWatchUser = new Watch("really expensive brand", 1000L) with ConnectorWithHelper {
//      override def connect(): Unit = {
//        println("Connected with another connector")
//      }
//
//      override def close(): Unit = {
//        println("Closed with another connector")
//      }
//    }
//
//    println("Using the really expensive watch.")
//    reallyExpensiveWatchUser.findDriver()
//    reallyExpensiveWatchUser.connect()
//    reallyExpensiveWatchUser.close()
//  }
//}
/*
Error:(5, 84) illegal inheritance; superclass Watch
 is not a subclass of the superclass Connector
 of the mixin trait ConnectorWithHelper
    val reallyExpensiveWatchUser = new Watch("really expensive brand", 1000L) with ConnectorWithHelper {
 */
