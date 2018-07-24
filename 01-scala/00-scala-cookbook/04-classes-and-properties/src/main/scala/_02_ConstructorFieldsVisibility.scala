/*
Controlling the Visibility of Constructor Fields

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s03.html

- If a field is declared as a var, Scala generates both getter and setter methods for that field.
- If the field is a val, Scala generates only a getter method for it.
- If a field doesn't have a var or val modifier, Scala gets conservative, and doesn't generate a getter or setter method for the field.
- Additionally, var and val fields can be modified with the private keyword, which prevents getters and setters from being generated.
 */
object _02_ConstructorFieldsVisibility {
  class Person(var name: String)
  class Dog(private var name: String, var age: Int)
  class Customer(val name: String)
  class Student(name: String)

  def main(args: Array[String]): Unit = {
    varFields

    valFields

    fieldsWithoutValAndVar

    addPrivateToVal
  }

  private def addPrivateToVal = {
    println("# adding private to val or var")
    val dog = new Dog("Rocky", 8)
    println(dog.age)
  }

  private def fieldsWithoutValAndVar = {
    println("# fields without val and var")
    val student = new Student("Harry")
    println(student)
  }

  private def valFields = {
    println("# val fields - getter")
    val customer = new Customer("Dick")
    println(customer.name)
  }

  private def varFields = {
    println("# var fields - getter and setter")
    val person = new Person("Tom")
    println("## getter")
    println(person.name)
    println("## setter")
    person.name = "Tommy"
    println(person.name)
  }
}
/*
# var fields - getter and setter
## getter
Tom
## setter
Tommy
# val fields - getter
Dick
# fields without val and var
ConstructorFieldsVisibility$Student@7cf10a6f
# adding private to val or var
8
 */
