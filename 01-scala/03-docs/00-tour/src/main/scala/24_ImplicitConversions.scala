/*
Implicit conversion

https://docs.scala-lang.org/tour/implicit-conversions.html

An implicit conversion from type S to type T is defined by an implicit value which has function type S => T, or by any implicit method convertible to a value of that type.

Implicit conversions are applied in two situations:
- If an expression e is of type S, and S does not conform to the expression's expected type T.
- In a selection e.m with e of type S, if the selector m does not denote a member of S.

youtube
https://www.youtube.com/watch?v=wMaLe4NuOS4
 */

case class HiString(s: String) {
  def sayHi() = {
    println("Hi, my name is " + s)
  }
}

object ImplicitConversions {
  def main(args: Array[String]) = {
    val name: String = "Mark"
    // Implicit conversion: convert string to HiString
    implicit def argString(s: String): HiString = HiString(s)
    name.sayHi()

//    val age: Int = 18
//    implicit def argInt(i: Int): HiString = HiString(i) // <- WON'T work due to type mismatch
//    age.sayHi()

  }
}
/*
Hi, my name is Mark
 */
