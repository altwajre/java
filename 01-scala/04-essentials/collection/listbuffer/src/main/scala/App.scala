import scala.collection.mutable.ListBuffer

/*
Use ListBuffer when you need a mutable List

https://alvinalexander.com/scala/how-to-create-mutable-list-in-scala-listbuffer-cookbook
 */
object App {
  def main(args: Array[String]) = {
    var fruits = new ListBuffer[String]()

    // add one element at a time to the ListBuffer
    fruits += "Apple"
    fruits += "Banana"
    fruits += "Orange"

    // add multiple elements
    fruits += ("Strawberry", "Kiwi", "Pineapple")
    println(fruits)

    // remove one element
    fruits -= "Apple"

    // remove multiple elements
    fruits -= ("Banana", "Orange")

    // remove multiple elements specified by another sequence
    fruits --= Seq("Kiwi", "Pineapple")

    // convert the ListBuffer to a List
    val fruitsList = fruits.toList
    println(fruitsList)
  }
}
/*
ListBuffer(Apple, Banana, Orange, Strawberry, Kiwi, Pineapple)
List(Strawberry)
 */
