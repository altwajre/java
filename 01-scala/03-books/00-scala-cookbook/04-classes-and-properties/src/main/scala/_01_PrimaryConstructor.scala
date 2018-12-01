/*
Creating a Primary Constructor

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s02.html

- The constructor parameters
- Methods that are called in the body of the class
- Statements and expressons
 */
object _01_PrimaryConstructor {
  class Person(var firstName: String, var lastName: String) {
    println("the constructor begins")

    // some class fields
    private val HOME = System.getProperty("user.home")
    var age = 0

    // some methods
    override def toString: String = s"$firstName $lastName is $age years old"
    def printHome { println(s"HOME = $HOME")}
    def printFullName {println(this)} // uses toString

    printHome
    printFullName
    println("still in the constructor")
  }

  def main(args: Array[String]): Unit = {
    val person = new Person("Adam", "Meyer")
  }
}
/*
the constructor begins
HOME = /Users/whan
Adam Meyer is 0 years old
still in the constructor
 */
