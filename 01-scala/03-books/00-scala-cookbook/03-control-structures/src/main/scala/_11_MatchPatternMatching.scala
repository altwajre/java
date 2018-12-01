/*
Using Pattern Matching in Match Expressions

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s12.html
 */
object _11_MatchPatternMatching {

  case class Person(firstName: String, lastName: String)

  case class Dog(name: String)

  def echoWhatYouGaveMe(x: Any): String = x match {
    // constant patterns
    case 0 => "zero"
    case true => "true"
    case "hello" => "you said 'hello'"
    case Nil => "an empty list"

    // sequence patterns
    case List(0, _, _) => "a three-element list with 0 as the first element"
    case List(1, _*) => "a list beginning with 1, having any number of elements"
    case Vector(1, _*) => "a vector starting with 1, having any number of elements"

    // tuples
    case (a, b) => s"two element tuples: ($a, $b)"
    case (a, b, c) => s"three element tuples: ($a, $b, $c)"

    // constructor patterns
    case Person(first, "Alexander") => s"found an Alexander, first name = $first"
    case Dog("Suka") => "found a dog named Suka"

    // typed patterns
    case s: String => s"string: $s"
    case i: Int => s"int: $i"
    case f: Float => s"float: $f"
    case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
    case as: Array[String] => s"an array of strings: ${as.mkString(",")}"
    case d: Dog => s"dog: ${d.name}"
    case list: List[_] => s"List: $list"
    case m: Map[_, _] => m.toString

    // the default wildcard pattern
    case _ => "Unknown"
  }

  def main(args: Array[String]): Unit = {
    println("# trigger the constant patterns")
    println(echoWhatYouGaveMe(0))
    println(echoWhatYouGaveMe(true))
    println(echoWhatYouGaveMe("hello"))
    println(echoWhatYouGaveMe(Nil))

    println("# trigger the sequence patterns")
    println(echoWhatYouGaveMe(List(0, 1, 2)))
    println(echoWhatYouGaveMe(List(1, 2)))
    println(echoWhatYouGaveMe(List(1, 2, 3)))
    println(echoWhatYouGaveMe(Vector(1, 2, 3)))

    println("# trigger the tuple patterns")
    println(echoWhatYouGaveMe(1, 2))
    println(echoWhatYouGaveMe("a", "b"))
    println(echoWhatYouGaveMe(1, "b", true))

    println("# trigger the constructor patterns")
    println(echoWhatYouGaveMe(Person("Melissa", "Alexander")))
    println(echoWhatYouGaveMe(Dog("Suka")))

    println("# trigger the typed patterns")
    println(echoWhatYouGaveMe("Hello, world"))
    println(echoWhatYouGaveMe(28))
    println(echoWhatYouGaveMe(28F))
    println(echoWhatYouGaveMe(Array(1, 2, 3)))
    println(echoWhatYouGaveMe(Array("coffee", "apple pie")))
    println(echoWhatYouGaveMe(Dog("Fido")))
    println(echoWhatYouGaveMe(List("apple", "banana")))
    println(echoWhatYouGaveMe(Map(1 -> "Tom", 2 -> "Dick")))

  }

}
/*
# trigger the constant patterns
zero
true
you said 'hello'
an empty list
# trigger the sequence patterns
a three-element list with 0 as the first element
a list beginning with 1, having any number of elements
a list beginning with 1, having any number of elements
a vector starting with 1, having any number of elements
# trigger the tuple patterns
two element tuples: (1, 2)
two element tuples: (a, b)
three element tuples: (1, b, true)
# trigger the constructor patterns
found an Alexander, first name = Melissa
found a dog named Suka
# trigger the typed patterns
string: Hello, world
int: 28
float: 28.0
an array of int: 1,2,3
an array of strings: coffee,apple pie
dog: Fido
List: List(apple, banana)
Map(1 -> Tom, 2 -> Dick)
 */
