/*
Looping over a Collection with foreach

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s10.html
 */
object _09_Foreach {
  def main(args: Array[String]): Unit = {
    println("# Vector")
    val x = Vector(1, 2)

    println("## x.foreach((i: Int) => println(i))")
    x.foreach((i: Int) => println(i))

    println("## x.foreach(i => println(i))")
    x.foreach(i => println(i))

    println("## x.foreach(println)")
    x.foreach(println)

    println("# String")
    "Hi".foreach(println)

    println("## split().foreach()")
    val longWords = new StringBuilder
    "where are you.".split(" ").foreach{
      e =>
        if(e.length > 4) longWords.append(s" $e")
        else println("Not added: " + e)
    }
    println(longWords)

    println("# Map")
    val map = Map("fname" -> "Tom", "lname" -> "Alex")
    map.foreach(x => println(s"${x._1} -> ${x._2}"))
  }

}
/*
# Vector
## x.foreach((i: Int) => println(i))
1
2
## x.foreach(i => println(i))
1
2
## x.foreach(println)
1
2
# String
H
i
## split().foreach()
Not added: are
Not added: you.
 where
# Map
fname -> Tom
lname -> Alex
 */
