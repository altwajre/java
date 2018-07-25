import model.Bar

/*
Controlling Method Scope

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s02.html

Scala methods are public by default; it provides following scope options:
- Object-private scope: the most restrictive, the method is only available to the current object instance
- Private: a) the current class, b) other instances of the current class (not subclass)
- Protected: the method is available to subclasses
- Package
- Package-specific
- Public

 */
object _01_MethodScope {

  // the most restrictive, the method is only available to the current object instance
  class ObjectPrivateScope {
    private[this] def isFoo = true
    def doFoo(other: ObjectPrivateScope): Unit = {
//      if(other.isFoo) {} // this line won't compile
    }
    def doFoo() = {
      if(isFoo) println("doFoo()")
    }
  }

  class PrivateScope {
    private def isFoo = true
    def doFoo(other: PrivateScope): Unit = {
      if(other.isFoo) {
        println("true")
      }
      else {
        println("false")
      }
    }
  }

  class ProtectedScope {
    protected def breathe {
      println("super class protected method")
    }
  }

  class Dog extends ProtectedScope {
    breathe // call super class protected method
  }

  def main(args: Array[String]): Unit = {
    println("# Object Private Scope")
    val objectPrivateScope = new ObjectPrivateScope
    objectPrivateScope.doFoo()

    println("# Private Scope")
    val privateScope = new PrivateScope
    privateScope.doFoo(new PrivateScope)

    println("# Protected Scope")
    new Dog

    println("# Package Scope")
    new Bar

  }
}
/*
# Object Private Scope
doFoo()
# Private Scope
true
# Protected Scope
super class protected method
# Package Scope
package scope
 */
