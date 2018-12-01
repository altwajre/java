/*
Splitting Strings

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s04.html
 */
object _03_Splitting {
  def main(args: Array[String]): Unit = {
    val strings: Array[java.lang.String] = "hello world".split(" ")
    println("# mkString")
    println(strings.mkString(" "))

    println("# foreach")
    "hello world".split(" ").foreach(println)

    val s = "eggs, milk, Coco Puffs"
    println("# split map trim")
    println(s.split(",").map(_.trim).foreach(println))

    println("# split on whitespace chars")
    println(s.split("\\s+").foreach(println))
  }
}
/*
# mkString
hello world
# foreach
hello
world
# split map trim
eggs
milk
Coco Puffs
()
# split on whitespace chars
eggs,
milk,
Coco
Puffs
()
 */
