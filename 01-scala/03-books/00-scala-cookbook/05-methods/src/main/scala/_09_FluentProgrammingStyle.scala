/*
Supporting a Fluent Style of Programming

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s10.html

Fluent programming style aka method chaining.

- If class can be extended, specify this.type as the return type of fluent style methods
- If class won't be extended, optionally return this from the fluent style methods
 */
object _09_FluentProgrammingStyle {

  class Person {
    protected var fname = ""
    protected var lname = ""

    def setFirstName(firstName: String): this.type = {
      fname = firstName
      this
    }

    def setLastName(lastName: String): this.type = {
      lname = lastName
      this
    }
  }

  class Employee extends Person {
    protected var role = ""

    def setRole(role: String): this.type = {
      this.role = role
      this
    }

    override def toString: String = {
      "%s, %s, %s".format(fname, lname, role)
    }
  }

  def main(args: Array[String]): Unit = {
    val employee = new Employee

    employee
      .setFirstName("Tom")
      .setLastName("Alexander")
      .setRole("Developer")

    println(employee)
  }
}
/*
Tom, Alexander, Developer
 */
