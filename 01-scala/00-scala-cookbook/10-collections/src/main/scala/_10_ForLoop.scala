/*
Looping over a Collection with a for Loop

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s11.html
 */
object _10_ForLoop {
  def main(args: Array[String]): Unit = {
    val customers = Vector("Tom", "Dick")
    println("# for(c <- customers)")
    for(c <- customers) println(c)

    println("# toUpperCase")
    for(c <- customers) println(c.toUpperCase)

    println("# Use a counter inside a for loop")
    for(i <- 0 until customers.size) println(s"element $i is ${customers(i)}")

    println("# Use zipWithIndex() for loop counter")
    for((elem, count) <- customers.zipWithIndex) {
      println(s"element $count is $elem")
    }

    println("# Use zip with a Stream")
    for((elem, count) <- customers.zip(Stream from 1)) {
      println(s"element $count is $elem")
    }

    println("# range")
    for(i <- 1 to 2) println(i)

    println("# for/yield construct")
    val reversedCustomers = for(c <- customers) yield c.toUpperCase.reverse
    println(reversedCustomers)

    println("# Map")
    val names = Map("fname" -> "Tom", "lname" -> "Alex")
    for((k,v) <- names) println(s"key: $k, value: $v")
  }
}
/*
# for(c <- customers)
Tom
Dick
# toUpperCase
TOM
DICK
# Use a counter inside a for loop
element 0 is Tom
element 1 is Dick
# Use zipWithIndex() for loop counter
element 0 is Tom
element 1 is Dick
# Use zip with a Stream
element 1 is Tom
element 2 is Dick
# range
1
2
# for/yield construct
Vector(MOT, KCID)
# Map
key: fname, value: Tom
key: lname, value: Alex
 */
