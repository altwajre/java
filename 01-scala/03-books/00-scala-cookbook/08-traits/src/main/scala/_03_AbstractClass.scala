/*
Using a Trait Like an Abstract Class

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s04.html
 */
object _03_AbstractClass {
  trait Pet {
    def speak {  // concrete implementation
      println("Yo")
    }
    def comeToMaster   // abstract method
  }
  class Dog extends Pet {
    override def comeToMaster: Unit = println("Dog: I'm coming!")
  }
  class Cat extends Pet {
    override def speak: Unit = println("meow") // override the speak method
    override def comeToMaster: Unit = println("Cat: No")
  }

  // class is defined as abstract if not implementing the abstract methods
  abstract class FlyingPet extends Pet {
    def fly {
      println("I'm flying!")
    }
  }
  class Bird extends FlyingPet {
    override def comeToMaster: Unit = println("Bird: on the way")
  }
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.comeToMaster

    val cat = new Cat
    cat.comeToMaster

    val bird = new Bird
    bird.comeToMaster
  }
}
/*
Dog: I'm coming!
Cat: No
Bird: on the way
 */
