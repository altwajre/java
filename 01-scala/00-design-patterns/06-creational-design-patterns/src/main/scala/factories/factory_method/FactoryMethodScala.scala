package factories.factory_method

/*
Implement the Factory Method in Scala with apply

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s10.html
 */
object FactoryMethodScala {
  trait Animal {
    def speak
  }
  object Animal {
    // the factory method
    def apply(s: String): Animal = {
      if(s == "dog") new Dog
      else new Cat
    }
  }

  class Dog extends Animal {
    override def speak: Unit = {
      println("woof")
    }
  }

  class Cat extends Animal {
    override def speak: Unit = {
      println("meow")
    }
  }

  def main(args: Array[String]): Unit = {
    val cat = Animal("cat") // use factory method to create cat
    cat.speak

    val dog = Animal("dog") // use factory method to create dog
    dog.speak
  }
}
/*
meow
woof
 */
