package com.company.app._06ScalaObject

//class access companion object private member
class SecretAgent(val name:String) {
  def shoot(n:Int): Unit = {
    import SecretAgent._ // import Companion object
    decrementBullets(n)
  }
}

object SecretAgent {
  private var b:Int = 3000
  private def decrementBullets(count:Int): Unit = {
    if(b - count <= 0) b = 0
    else b = b - count
  }
  def bullets = b
}

object ClassAccessCompanionObjectRunner extends App {
  val tom = new SecretAgent("Tom")
  val dick = new SecretAgent("Dick")
  val harry = new SecretAgent("Harry")

  // they use the shared bullets 3000 in the companion object `object SecretAgent`
  tom.shoot(800)
  dick.shoot(300)
  harry.shoot(200)

  println(SecretAgent.bullets) // see how many bullets left
  // 1700
}

//====================================================

// companion object access class private member
class Person(val name:String, private val superheroName:String)

object Person {
  def showMeInnerSecret(x:Person) = x.superheroName
}

object CompanionObjectAccessClassRunner extends App {
  val tom = new Person("Tom", "Superman")
  println(Person.showMeInnerSecret(tom))
  // Superman
}

//====================================================

// companion object as factory
import java.time._
// `protected` so test can access it
class Employee protected (val firstName:String, val lastName:String, val title:String, val hireDate:LocalDate)

object Employee {
  def create(firstName:String, lastName:String, title:String) =
    new Employee(firstName, lastName, title, LocalDate.now)

  def create(firstName:String, lastName:String, title:String, hireDate:LocalDate) =
    new Employee(firstName, lastName, title, hireDate)
}

object FactoryRunner extends App {
  val employee = Employee.create("Tom", "Lee", "Programmer")
  println(employee.hireDate)
}
