/*
Handling Constructor Parameters When Extending a Class

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s11.html

age is the subclass (Employee) constructor parameter, so declare it as a var
var age: Int

 */
object _10_SubclassConstructorParams {
  case class Address (city: String, state: String)

  class Person(var name: String, var address: Address) { // base class
    override def toString: String = if(address == null) name else s"$name @ $address"
  }

  /*
  age is the subclass constructor parameter, so declare it as a var
  var age: Int
   */
  class Employee(name: String, address: Address, var age: Int) extends Person(name, address) {

  }

  def main(args: Array[String]): Unit = {
    val teresa = new Employee("Teresa", Address("Louisville", "KY"), 25)
    println(teresa.name)
    println(teresa.address)
    println(teresa.age)
  }
}
/*
Teresa
Address(Louisville,KY)
25
 */
