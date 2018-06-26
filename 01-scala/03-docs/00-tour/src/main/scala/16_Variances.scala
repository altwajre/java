/*
Variances

https://docs.scala-lang.org/tour/variances.html

https://www.coursera.org/lecture/progfun1/lecture-4-4-variance-optional-dnreZ

C[T] is a parameterized type and A, B are types such that A <: B

Three possible relationships between C[A] and C[B]

C[A] <: C[B] (B extends A - B is subtype of A)      C is covariant
C[A] >: C[B] (A extends B)                          C is contravariant
neither C[A] nor C[B] is a subtype of the other     C is invariant (nonvariant)

Declare the variance of a type by annotating the type parameter:

class C[+A] {...}                                   C is covariant
class C[-A] {...}                                   C is contravariant
class C[A] {...}                                    C is invariant (nonvariant)
 */

case class Bar1()

object Variances {
  def main(args: Array[String]) = {
    println("# Covariance - def printAnimalNames(animals: List[Animal])")
    abstract class Animal {
      def name: String
    }
    case class Cat(name: String) extends Animal
    case class Dog(name: String) extends Animal

    def printAnimalNames(animals: List[Animal]) = {
      animals.foreach {
        animal => println(animal.name)
      }
    }

    val cats: List[Cat] = List(Cat("Tom"), Cat("Harry"))
    val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

    println("## Cats")
    printAnimalNames(cats)

    println("## Dogs")
    printAnimalNames(dogs)

    println("# Contravariance - def printMyCat(printer: Printer[Cat])")
    abstract class Printer[-A] {
      def print(value: A): Unit
    }
    class AnimalPrinter extends Printer[Animal] {
      def print(animal: Animal) = println("The animal's name is: " + animal.name)
    }
    class CatPrinter extends Printer[Cat] {
      def print(cat: Cat) = println("The cat's name is: " + cat.name)
    }
    class DogPrinter extends Printer[Dog] {
      def print(dog: Dog) = println("The dog's name is: " + dog.name)
    }

    val myCat: Cat = Cat("Boots")

    def printMyCat(printer: Printer[Cat]) = {
      printer.print(myCat)
    }

    // Cat extends Animal, that is why need Printer[-A] for def printMyCat(p: Printer[Cat])
    val catPrinter: Printer[Cat] = new CatPrinter
    val animalPrinter: Printer[Animal] = new AnimalPrinter
    val dogPrinter: Printer[Dog] = new DogPrinter

    printMyCat(catPrinter)
    printMyCat(animalPrinter)
//    printMyCat(dogPrinter) // <- WON"T work because def printMyCat(p: Printer[Cat]) is a Cat type

    println("# Invariance - class Container[A](value: A)")

    class Container[A](value: A) {
      private val _value: A = value
      def getValue: A = _value
//      def setValue(value: A) = _value = value
    }

    val catContainer: Container[Cat] = new Container(Cat("Felix"))
    println(catContainer.getValue)

    // Following code WON'T work because it is invariance class C[A] {...}
//    val animalContainer: Container[Animal] = catContainer
//    println(animalContainer.getValue)
    // It will work when it is Covariance class Container[+A](value: A)
  }
}
/*
# Covariance - def printAnimalNames(animals: List[Animal])
## Cats
Tom
Harry
## Dogs
Fido
Rex
# Contravariance - def printMyCat(printer: Printer[Cat])
The cat's name is: Boots
The animal's name is: Boots
# Invariance - class Container[A](value: A)
Cat(Felix)
 */
