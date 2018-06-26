/*
Type Inference

https://docs.scala-lang.org/tour/type-inference.html

When not to rely on type inference
It is more readable to declare the type of members exposed in a public API.
 */

case class Bar5()

object TypeInference {
  def main(args: Array[String]) = {
    println("# Omitting the type")
    val name = "Tom"
    println(name)

    def squareOf(x: Int) = x * x
    println(squareOf(3))
  }
}
/*
# Omitting the type
Tom
9
 */
