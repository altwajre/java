package _06_lazy_evaluation

/*
Lazy evaluation

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/194de329-0643-48f9-87b0-7ad16b75e15d.xhtml

Lazy evaluation makes sure that an expression is evaluated only once when it is actually needed.

It is for Creational Design Patterns - lazy initialization

- Lazy variables: will be calculated only once
- By-name parameters will be calculated every time they are referred to in a method

 */
object EvaluatingByNameParametersOnlyOnce {
  case class Person(name: String, age: Int)

  object Person {
    def getFromDatabase(): List[Person] = {
      // simulate we're getting people from database by sleeping
      System.out.println("Retrieving people...")
      Thread.sleep(3000)
      List(
        Person("Ivan", 26),
        Person("Maria", 26),
        Person("John", 25)
      )
    }

    def printPeopleBad(people: => List[Person]): Unit = {
      println("# printPeopleBad")
      System.out.println(s"Print first time: ${people}")
      System.out.println(s"Print second time: ${people}")
    }

    def printWithLazy(people: => List[Person]): Unit = {
      println("# printWithLazy good")
      lazy val peopleCopy = people
      println(s"Print first time: ${peopleCopy}")
      println(s"Print second time: ${peopleCopy}")
    }

    /*
    anonymous functions lazy evaluation
    https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/9c09d6c6-d6ad-4d57-ad21-f44c40bd8609.xhtml
     */
    def printWithAnonymousFunction(people: => List[Person]): Unit = {
      println("# printWithAnonymousFunction not recommended")
      val peopleCopy = () => people
      println(s"Print first time: ${peopleCopy()}")
      println(s"Print second time: ${peopleCopy()}")
    }
  }

  import Person._
  def main(args: Array[String]): Unit = {
    printPeopleBad(getFromDatabase())

    printWithLazy(getFromDatabase())
    printWithAnonymousFunction(getFromDatabase())
  }
}
/*
# printPeopleBad
Retrieving people...
Print first time: List(Person(Ivan,26), Person(Maria,26), Person(John,25))
Retrieving people...
Print second time: List(Person(Ivan,26), Person(Maria,26), Person(John,25))
# printWithLazy good
Retrieving people...
Print first time: List(Person(Ivan,26), Person(Maria,26), Person(John,25))
Print second time: List(Person(Ivan,26), Person(Maria,26), Person(John,25))
# printWithAnonymousFunction good
Retrieving people...
Print first time: List(Person(Ivan,26), Person(Maria,26), Person(John,25))
Retrieving people...
Print second time: List(Person(Ivan,26), Person(Maria,26), Person(John,25))
 */
