/*
Substituting Variables into Strings

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s05.html
 */
object _04_SubstitutingVariables {

  case class Student(name: String, score: Int)

  def main(args: Array[String]): Unit = {
    println("# variables")
    val name = "Fred"
    val age = 28
    val weight = 200.00
    println(s"$name is $age years old, and weights $weight pounds.")

    println("# expressions")
    println(s"Age next year: ${age + 1}")

    println("# object fields")
    val tom = Student("Tom", 28)
    println(s"${tom.name} has a score of ${tom.score}")

    println("# Raw Interpolator")
    println(s"foo\nbar")
    println(raw"foo\nbar")

  }
}
/*
# variables
Fred is 28 years old, and weights 200.0 pounds.
# expressions
Age next year: 29
# object fields
Tom has a score of 28
# Raw Interpolator
foo
bar
foo\nbar
 */
