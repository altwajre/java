/*
Upper Type Bounds

https://docs.scala-lang.org/tour/upper-type-bounds.html

class PetContainer[P <: Pet](p: P)
 */
abstract class Animal {
  def name: String
}

abstract class Pet extends Animal {}

class Cat extends Pet {
  override def name: String = "Cat"
}

class Dog extends Pet {
  override def name: String = "Dog"
}

class Lion extends Animal {
  override def name: String = "Lion"
}

// take type parameter P which must be a subtype of Pet
class PetContainer[P <: Pet](p: P) {
  def pet: P = p
}

object UpperTypeBounds {
  def main(args: Array[String]) = {
    val dogContainer = new PetContainer[Dog](new Dog)
    println(dogContainer.pet.name)
    val catContainer = new PetContainer[Cat](new Cat)
    println(catContainer.pet.name)

//    val lionContainer = new PetContainer[Lion](new Lion) // compile error
  }
}
/*
Dog
Cat
 */
