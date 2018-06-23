/*
Unified Types

https://docs.scala-lang.org/tour/unified-types.html

all values have a type, including numerical values and functions.
                             Any
                        /           \
                     AnyVal        AnyRef
                        \          |     \
                        Int       List  CustomClass
                          \         \    /
                           \         Null
                            \        /
                             Nothing

- Nothing
It is a subtype of all types, also called the bottom type.
signal non-termination such as throw exception, project exit, or an infinite loop.

- Null
It is a subtype of all reference types.
It has a single value identified by the keyword literal null.
 */

object UnifiedTypes {
  def main(args: Array[String]) = {
    val list: List[Any] = List(
      "hello", // string
      123, // integer
      'c', // character
      true, // boolean
      () => "anonymous function"
    )

    list.foreach(e => println(e))
  }
}

/*
hello
123
c
true
UnifiedTypes$$$Lambda$5/1908923184@1d251891
 */

/*
Value types can be cast in the following way

Byte -> Short -> Int -> Long -> Float -> Double
                  ^
                  |
                 Char
 */
object TypeCasting {
  def main(args: Array[String]) = {
    val x: Long = 123
    val y: Float = x
    println(y)

    val c: Char = 'A'
    val number: Int = c
    println(number)
  }
}

/*
123.0
65
 */
