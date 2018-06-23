package polymorphism

/*
Subtype polymorphism

Subtype polymorphism is expressed using inheritance with the extends keyword.

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/01b87664-0207-48f0-a50c-eedab26d548c.xhtml

We can use the abstract type and just call the pack method without thinking about what exactly it is.
Polymorphism will take care of printing the correct value.
 */
abstract class Item {
  def pack: String
}

class Fruit extends Item {
  override def pack: String = {
    "I'm a fruit and I'm packed in a bag."
  }
}

class Drink extends Item {
  override def pack: String = {
    "I'm a drink and I'm packed in a bottle."
  }
}

object SubtypePolymorphism {
  def main(args: Array[String]) = {
    val shoppingBasket: List[Item] = List( // abstract type Item
      new Fruit,
      new Drink
    )
    shoppingBasket.foreach(i => println(i.pack))
  }

}
/*
I'm a fruit and I'm packed in a bag.
I'm a drink and I'm packed in a bottle.
 */
