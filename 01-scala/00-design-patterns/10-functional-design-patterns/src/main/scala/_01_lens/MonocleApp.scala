package _01_lens

import monocle.Lens
import monocle.macros.GenLens

/*
Minimizing the boilerplate - Monocle

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/87e4e48e-26b2-4e32-8c58-2cecd74afd84.xhtml

http://julien-truffaut.github.io/Monocle/
https://github.com/julien-truffaut/Monocle

 */
object MonocleApp {
  case class Street(number: Int, name: String)
  case class Address(city: String, street: Street)
  case class Company(name: String, address: Address)
  case class User(name: String, company: Company)

  object User {
    val company   : Lens[User, Company] = GenLens[User](_.company)
    val address   : Lens[Company, Address] = GenLens[Company](_.address)
    val street    : Lens[Address, Street] = GenLens[Address](_.street)
    val streetName: Lens[Street, String] = GenLens[Street](_.name)
  }
  def main(args: Array[String]): Unit = {

    val street = Street(23, "high street")
    val address = Address("london", street)
    val company = Company("yahoo inc", address)
    val user = User("john", company)
    println(user)

    val newUer = (User.company composeLens User.address composeLens User.street composeLens User.streetName).modify(_.capitalize)(user)
    println(newUer)



  }
}
/*
Employee(john,Company(awesome inc,Address(london,Street(23,high street))))
Employee(john,Company(awesome inc,Address(london,Street(23,High street))))
 */
