/*
Calling a Method on a Superclass

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s03.html
 */
object _02_CallSuperclassMethod {
  class FourLeggedAnimal {
    def walk {
      println("super class walking")
    }
    def run: Unit = {
      println("super class running")
    }
  }

  class Dog extends FourLeggedAnimal {
    def walkThenRun: Unit = {
      super.walk
      super.run
    }
  }

  trait Human {
    def hello = "the Human trait"
  }

  trait Mother extends Human {
    override def hello = "Mother"
  }

  trait Father extends Human {
    override def hello: String = "Father"
  }

  class Child extends Human with Mother with Father {
    def printSuper = super.hello
    def printMother = super[Mother].hello
    def printFather = super[Father].hello
    def printHuman = super[Human].hello
  }

  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.walkThenRun

    println("# Control which trait to call")

    val c = new Child
    println(s"c.printSuper = ${c.printSuper}")
    println(s"c.printMother = ${c.printMother}")
    println(s"c.printFather = ${c.printFather}")
    println(s"c.printHuman = ${c.printHuman}")
  }
}
/*
super class walking
super class running
# Control which trait to call
c.printSuper = Father
c.printMother = Mother
c.printFather = Father
c.printHuman = the Human trait
 */
