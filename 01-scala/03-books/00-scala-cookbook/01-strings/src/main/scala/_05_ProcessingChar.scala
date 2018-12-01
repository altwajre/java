/*
Processing a String One Character at a Time

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s06.html
 */
object _05_ProcessingChar {
  def main(args: Array[String]): Unit = {
    println("# .map(c => c.toUpper)")
    val upper = "hello, world".map(c => c.toUpper)
    println(upper)

    println("# .map(_.toUpper)")
    println("hello, world".map(_.toUpper))

    println("# .filter().map(_.toUpper)")
    println("hello, world"
      .filter(_ != 'l')
      .map(_.toUpper))

    println("""# for (c <- "hello")""")
    for (c <- "hello") println(c)


    println("""# for() yield""")
    println(for (c <- "hello, world") yield c.toUpper)

    println("""# for{if} yield""")
    println(for {
      c <- "hello, world"
      if c != 'l'
    } yield c.toUpper)

    println("# foreach()")
    "hello".foreach(println)
  }

}
/*
# .map(c => c.toUpper)
HELLO, WORLD
# .map(_.toUpper)
HELLO, WORLD
# .filter().map(_.toUpper)
HEO, WORD
# for (c <- "hello")
h
e
l
l
o
# for() yield
HELLO, WORLD
# for{if} yield
HEO, WORD
# foreach()
h
e
l
l
o
 */
