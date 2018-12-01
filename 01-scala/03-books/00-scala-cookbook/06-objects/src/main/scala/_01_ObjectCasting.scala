/*
Object Casting

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s02.html
 */
object _01_ObjectCasting {
  class Person(name: String, age: Int) {
    override def toString: String = s"base class: name=$name, age=$age"
  }
  class Employee(name: String, age: Int, role: String) extends Person(name, age) {
    override def toString: String = s"subclass: name=$name, age=$age, role=$role"
  }

  def objectCasting(person: Person): Unit = {
    val e1 = person.asInstanceOf[Employee]
    println(e1)
  }

  def main(args: Array[String]): Unit = {
    objectCasting(new Employee("Tom", 28, "Dev"))
  }
}
/*
subclass: name=Tom, age=28, role=Dev
 */
