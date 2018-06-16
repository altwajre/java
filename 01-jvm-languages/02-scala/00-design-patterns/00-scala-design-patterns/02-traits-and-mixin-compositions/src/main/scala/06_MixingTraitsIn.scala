case object Foo

/*
Mixing traits in

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/7b93efc0-b20d-4152-8c6c-af81550330f0.xhtml

We use object and main method which is similar to create a class with no constructor parameters (objects in Scala are singleton classes).

 */
object MixingTraitsIn extends Ping with Pong {
  def main(args: Array[String]): Unit = {
    ping() // // call trait method directly
    pong()
  }
}
/*
ping
pong
 */
