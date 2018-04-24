class Person(val name:String, private val superheroName:String)

object Person {
  def showMeInnerSecret(x:Person) = x.superheroName
}

object SuperHeroRunner extends App {
  val tom = new Person("Tom", "Superman")
  println(Person.showMeInnerSecret(tom))
}

//$ scala 02-SuperHeroes.scala
//Superman
