import scala.collection.mutable

/*
Adding Elements to a Set

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s25.html
 */
object SetApp {
  def main(args: Array[String]): Unit = {
    println("# mutable set")

    val mutableSet = mutable.Set[Int]()

    println("- add one element")
    mutableSet += 1
    println(mutableSet)

    println("- add multiple elements")
    mutableSet += (2, 3)
    println(mutableSet)

    println("- add elements from any seq")
    mutableSet ++= Vector(4, 5)
    println(mutableSet)

    println("- .add()")
    mutableSet.add(6)
    println(mutableSet)

    println("- .contains()")
    println(mutableSet.contains(5))

    println("- delete one element")
    mutableSet -= 1
    println(mutableSet)

    println("- delete multiple elements")
    mutableSet -= (2, 3)
    println(mutableSet)

    println("- delete multiple elements in another Seq")
    mutableSet --= Array(4, 5)
    println(mutableSet)

    println("# immutable set")
    val s1 = Set(1, 2)
    println(s1)

    println("- add one element")
    val s2 = s1 + 3
    println(s2)

    println("- add multiple elements")
    val s3 = s2 + (4, 5)
    println(s3)

    println("- add elements from another Seq")
    val s4 = s3 ++ List(6, 7)
    println(s4)

    println("- delete one element")
    val s5 = s4 - 1
    println(s5)

    println("- delete multiple elements")
    val s6 = s5 - (2, 3)
    println(s6)

    println("- delete multiple elements in another Seq")
    val s7 = s6 -- Array(4, 5)
    println(s7)
  }
}
/*
# mutable set
- add one element
Set(1)
- add multiple elements
Set(1, 2, 3)
- add elements from any seq
Set(1, 5, 2, 3, 4)
- .add()
Set(1, 5, 2, 6, 3, 4)
- .contains()
true
- delete one element
Set(5, 2, 6, 3, 4)
- delete multiple elements
Set(5, 6, 4)
- delete multiple elements in another Seq
Set(6)
# immutable set
Set(1, 2)
- add one element
Set(1, 2, 3)
- add multiple elements
Set(5, 1, 2, 3, 4)
- add elements from another Seq
Set(5, 1, 6, 2, 7, 3, 4)
- delete one element
Set(5, 6, 2, 7, 3, 4)
- delete multiple elements
Set(5, 6, 7, 4)
- delete multiple elements in another Seq
Set(6, 7)
 */
