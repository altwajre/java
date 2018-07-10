# Access object members

- package object

Access its members from the classes in the same package

package object mypackage {
  def helloFromPackageObject() = {
    println("Hello from my mypackage object")
  }
}

- import ObjectName._

Use import ObjectName._ if it is a simple object
