import scala.collection.mutable.ArrayBuffer

object Trait {
  def main(args: Array[String]) = {
    println("# Defining a trait")
    trait HairColor
    val color = new HairColor {}
    println(color.getClass.getSimpleName)

    println("# Using traits")
    trait Iterator[A] {
      def hasNext: Boolean
      def next(): A
    }

    class IntIterator(to: Int) extends Iterator[Int] {
      private var current = 0

      override def hasNext: Boolean = current < to

      override def next(): Int = {
        if(hasNext) {
          val t = current
          current += 1
          t
        }
        else 0
      }
    }

    val iterator = new IntIterator(3)
    println(iterator.next())
    println(iterator.next())
    println(iterator.next())
    println(iterator.next())

    println("# Subtyping")

    trait Pet {
      val name: String
    }

    class Cat(val name: String) extends Pet
    class Dog(val name: String) extends Pet

    val dog = new Dog("Dog: Tom")
    val cat = new Cat("Cat: Harry")

    val animals = ArrayBuffer.empty[Pet]
    animals.append(dog)
    animals.append(cat)
    animals.foreach(pet => println(pet.name))
  }
}
/*
# Defining a trait
anon$1
# Using traits
0
1
2
0
# Subtyping
Dog: Tom
Cat: Harry
 */
