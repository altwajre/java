/*
Looping with for and foreach

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s02.html
 */
object _01_ForAndForeach {
  def main(args: Array[String]): Unit = {
    val a = Array("apple", "banana", "orange")

    println("# Inline")
    for (e <- a) println(e)

    println("# Multiple lines")
    for (e <- a) {
      // imagine this requires multiple lines
      val s = e.toUpperCase
      println(s)
    }

    println("# Return value from for loop")
    val newArray = for (e <- a) yield e.toUpperCase
    newArray.foreach(println)

    println("# For loop counters")
    for (i <- 0 until a.length) {
      println(s"$i is ${a(i)}")
    }

    println("# for zipWithIndex")
    for ((e, count) <- a.zipWithIndex) {
      println(s"$count is $e")
    }

    println("# for guards")
    for (i <- 1 to 10 if i < 4) println(i)

    println("# Mpa iteration")
    val names = Map("fname" -> "Tom",
      "lname" -> "Lee")
    for ((k, v) <- names) println(s"key: $k, value: $v")
  }
}

/*
# Inline
apple
banana
orange
# Multiple lines
APPLE
BANANA
ORANGE
# Return value from for loop
APPLE
BANANA
ORANGE
# For loop counters
0 is apple
1 is banana
2 is orange
# for zipWithIndex
0 is apple
1 is banana
2 is orange
# for guards
1
2
3
key: fname, value: Tom
key: lname, value: Lee
 */
