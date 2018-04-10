# scala

## Strings

- Strings are objects
- Strings are surrounded by double quotes
- Strings have all the basic functionality of other collections
- Strings may also contain escape characters

```
scala> val a = "Scala"
a: String = Scala

scala> val a:String = "Scala"
a: String = Scala

scala> val a = "Scala":String
a: String = Scala

scala> val escapes = "\t\n\r\u03BB"
escapes: String =
"
λ"
```

> String Formatting

- format can be used to do C-Style String Formatting
- %s will be replaced by a String
- Positions in a format can be performed with %{position}${format}

```
"%s style".format("Scala")
```

> String Interpolation

- String Interpolation performs variable replacement
- String Interpolation requires as s or f before a String
- Braces may be required for evaluation
- Use f interpolator to use C/Java-Style formatting
- Works great with Scala Smart Strings

```
val a = 8
println(s"${a} dollars") // s"" means using string interpolation
```

## Basic Methods

- Methods are defined using def
- Methods are not functions
- return keyword is unnecessary
- Last evaluated statement will be returned
- Most of the time you can omit the return type

> Reasons to include return types

- Make intentions clear
- Type inferencer inferred the wrong type
- Your method will be called recursively
- Your method is overloaded

> Any, AnyVal, AnyRef

- Any is the utmost super type and is the parent of everything
- AnyVal is the parent of the primitive types
- AnyRef is the same as java.lang.Object in Java
- AnyRef is the parent of all classes written in Java and Scala

```
        - scala.Any -
        |           |
 scala.AnyVal    scala.AnyRef
```

## Unit And Unit Conventions

`Unit` means `void` in Java

- Unit doesn't give you anything
- Similar to Java's void
- Unit is actually an object
- Unit's full type name is scala.Unit
- Unit has one value: `()`
- Whenever you see () that is a Unit

*Unit*

`Unit` is subtype of `scala.AnyRef`

```
scala> ()

scala> val g = ()
g: Unit = ()

scala> val g:Unit = ()
g: Unit = ()

scala> val a = println("Scala Rocks")
Scala Rocks
a: Unit = ()
```

## Tail Optimized Recursion

- Recursive methods may run our of stack memory
- Convert recursive method into a tail recursive method
- Tail recursive methods after evaluation must be returned
- Use @scala.annotation.tailrec for compilation assistance

```
@tailrec
def fact(n:BigInt, acc:BigInt):BigInt = {
  if (n == 0 || n == 1) acc
  else fact(n -1, acc * n)
}
def factorial(n:Int) = fact(n, 1)
println(factorial(5))
```

## Methods In Methods

- Methods can be hidden inside another method
- The outside method has exclusive access to the inner method

```
def factorial(n:Int) = {
  def fact(n:BigInt, acc:BigInt):BigInt = {
    if(n == 0 || n == 1)acc
    else fact(n-1, acc * n)
  }
  fact(n, 1)
}
println(factorial(5))
// 120
```

## Named And Default Arguments

- Named arguments allow calls by method parameter name
- Named arguments allow calls in any order
- Default Arguments specify default values in the method declaration
- In case default arguments are difficult to call, use named arguments to assist

## AsInstanceOf And IsInstanceOf

- isInstanceOf will return Boolean whether object is an instance of a type
- asInstanceOf will convert object to a certain type
- asInstanceOf will require that object is convertible to a type
- Misuse of asInstanceOf will result in a ClassCastException

## Parameterized Types On Methods

- Parameterized Types maintain type consistency
- Parameterized Types use [] not <>
- Parameterized Types are nearly equivalent to Java generics
- Parameterized Types are nearly equivalent to C++ templates
- Prefer use of single letters: A, B, C, D, E, T

*non generic type method*

```
scala> def decide(b:Boolean, x:Any, y:Any) = if (b) x else y
decide: (b: Boolean, x: Any, y: Any)Any

scala> decide(true, 'a', 'b')
res16: Any = a
```

*generic type method*

```
scala> def decide[T](b:Boolean, x:T, y:T) = if (b) x else y
decide: [T](b: Boolean, x: T, y: T)T

scala> decide(true, 'a', 'b')
res17: Char = a
```

## Java Getters and Setters

- Use @scala.beans.BeanProperty
- Apply BeanProperty annotation to the property
- If applied to a val, BeanProperty will create a getter
- If applied to a var, BeanProperty will create a setter

```
class Employee(@BeanProperty val firstName:String, @BeanProperty var lastName:String)
val ada = new Employee("Ada", "Lovelace")
ada.lastName = "Lee"
println(ada.lastName)
```

## Constructors

- Ancillary Constructors look like methods named this
- Primary Constructors are designed for all information up front
- Ancillary Constructors need to find a way to invoke the primary constructor
- Call another constructor by invoking this(...)
- If an ancillary constructor is multi-lined, the first line must be a call to this(...)

```
class Employee(@BeanProperty val firstName: String,
               @BeanProperty var lastName: String,
               val title: String) {
  def this(firstName: String, lastName: String) = this(firstName, lastName, "Programmer")
}
val ada = new Employee("Ada", "Lovelace")
println(ada.firstName)
// Ada
ada.lastName = "Lee"
println(ada.lastName)
// Lee
println(ada.title)
// Programmer
```

## Preconditions, Exceptions, And Exception Handling

- require checks for quality of parameters in a method or constructor
- require will throw an IllegalArgumentException
- try and catch uses pattern matching to determine if a particular Exception is thrown
- Exceptions are thrown with the keyword throw and an Exception object

```
class Employee(@BeanProperty val firstName: String,
               @BeanProperty val lastName: String,
               val title: String = "Programmer") {
  require(firstName.nonEmpty, "First Name cannot be empty")
  require(lastName.nonEmpty, "Last Name cannot be empty")
  require(title.nonEmpty, "Title cannot be empty")
  if(title.contains("Senior") || title.contains("Junior"))
    throw new IllegalArgumentException("Title cannot contain Junior or Senior")
}
try {
  new Employee("Tom", "Lee", "Senior C Programmer")
} catch {
  case iae:IllegalArgumentException => println(iae.getMessage)
} finally {
  println("Continuing with our program 2")
}
//Title cannot contain Junior or Senior
//Continuing with our program 2
```

## Subclassing

- extends is a keyword used to subclass a class from another class
- Attributes belonging to super class are sent to the superclass's constructor
- Inheritance must have a "is-a" relationship
- All subclasses are polymorphic, by assignment or method call
- You can have multiple classes in one file

```
class Employee(@BeanProperty val firstName: String,
               @BeanProperty val lastName: String,
               val title: String = "Programmer") {
  require(firstName.nonEmpty, "First Name cannot be empty")
  require(lastName.nonEmpty, "Last Name cannot be empty")
  require(title.nonEmpty, "Title cannot be empty")
  if (title.contains("Senior") || title.contains("Junior"))
    throw new IllegalArgumentException("Title cannot contain Junior or Senior")
}
class Department(val name: String)
class Manager(firstName: String, lastName: String, title: String, val department: Department) extends
  Employee(firstName, lastName, title)
val mathDepartment = new Department("Mathematics")
val manager = new Manager("Tom", "Lee", "Mathematics", mathDepartment)
println(manager.department.name)
// Mathematics
val managerRef:Employee = manager
println(manager.firstName)
println(managerRef.firstName)
// Tom
// Tom
println("Polymorphism")
def extractFirstName(e:Employee) = {
  println(e.getClass.getName)
  // com.company.app._05Clazzes._07Subclassing$$anonfun$1$Manager$1
  e.firstName
}
println(extractFirstName(manager))
println(extractFirstName(managerRef))
```

## Overriding Methods

- override keyword is mandatory
- Overridden methods must look the same as the superclass method is is overriding
- Overridden methods are created to change the implementation of the superclass's methods
- Regardless of reference the correct method will be called

```
class Employee(@BeanProperty val firstName: String,
               @BeanProperty val lastName: String,
               val title: String = "Programmer") {
  require(firstName.nonEmpty, "First Name cannot be empty")
  require(lastName.nonEmpty, "Last Name cannot be empty")
  require(title.nonEmpty, "Title cannot be empty")
  if (title.contains("Senior") || title.contains("Junior"))
    throw new IllegalArgumentException("Title cannot contain Junior or Senior")
  def fullName = s"$firstName $lastName"
  def copy(firstName: String = this.firstName, lastName: String = this.lastName, title: String = this.title) = {
    new Employee(firstName, lastName, title)
  }
}
class Department(val name: String)
class Manager(firstName: String, lastName: String, title: String, val department: Department) extends
  Employee(firstName, lastName, title) {
  override def fullName: String = s"$firstName $lastName, ${department.name} Manager"
}
val mathDepartment = new Department("Mathematics")
val manager = new Manager("Tom", "Lee", "Mathematics", mathDepartment)
println(manager.department.name)
// Mathematics
val managerRef:Employee = manager
println(managerRef.fullName)
// Tom Lee, Mathematics Manager
```

## Case Classes

- Placing case keyword in front of class makes it a case class
- Case classes have an automatic equals, toString, and hashCode
- You can instantiate a class without the new keyword
- If you don't like the methods created. Override your own.
- You can have automatic pattern matching with case classes
- Copy method to 'clone' instances

## Parameterized Types on Classes

- Parameterized Types use square brackets just like in methods
- Parameterized Types are managed by the type system
- You can have as many parameterized types on a class as needed
- A Parameterized Type can contain another Parameterized Type

```
test("Box") {
  case class Box[T](t:T)
  val intBox = new Box(1) // Box[Int]
  println(intBox.t)
  // 1
  val intBox2:Box[Int] = Box(1) // Box[Int]
  val intBox3 = Box(1):Box[Int] // Coercion Box[Int]; Coercion means force
  val intResult:Int = intBox3.t
  println(intResult)
  // 1
  val stringBox = new Box("Hello")
  val stringBoxExplicit = Box[String]("Hello")
  println(stringBox.t)
  // hello
  println(stringBoxExplicit.t)
  // hello
  val managerBox = Box[Employee](
    new Manager("Tom", "Lee", "Programmer",
      new Department("Programming"))) // Box[Employee]
  println(managerBox.t.firstName)
  // Tom
}
test("Couple") {
  case class Couple[A, B](first:A, second:B)
  val coupleIntString = new Couple(10, "Scala") // Couple[Int, String]
  println(coupleIntString.first)
  println(coupleIntString.second)
}
```

## Singleton Objects

- Objects are singletons
- Objects are Scala's replacement for the keyword static
- Objects are meant for factories, defining pattern matching, defining defaults, and main methods
- Main methods are always inside of objects
- You can forget the main declaration by having your object extend App

> Classes

- Need to define a template to create multiple instances
- Every instance is in charge of it's own state

> Objects

- Need a singleton
- Need a factory pattern
- Need to implement pattern matching logic
- Need a utility method that doesn't require an instance or state
- Need default values

```
object MyMath {
  def add(x:Int, y:Int) = x + y
}
println(MyMath.add(2, 3))
// 5
case class Employee(firstName:String, lastName:String, title:String)
object tom extends Employee("Tom", "Lee", "Programmer")
println(tom.firstName)
// Tom
```

## Companion Objects

It is like friend class can access other class private fields
We can use `Companion Object` as `Factory`

- Companion Objects have the same name as the class they represent
- Companion Objects must be in the same file as the class they represent
- Companion Object have access to their representative class's private information
- Classes have access to the companion object's private information

```
//class access companion object private member
class SecretAgent(val name:String) {
  def shoot(n:Int): Unit = {
    import SecretAgent._ // import Companion object
    decrementBullets(n)
  }
}
object SecretAgent {
  private var b:Int = 3000
  private def decrementBullets(count:Int): Unit = {
    if(b - count <= 0) b = 0
    else b = b - count
  }
  def bullets = b
}
object ClassAccessCompanionObjectRunner extends App {
  val tom = new SecretAgent("Tom")
  val dick = new SecretAgent("Dick")
  val harry = new SecretAgent("Harry")
  // they use the shared bullets 3000 in the companion object `object SecretAgent`
  tom.shoot(800)
  dick.shoot(300)
  harry.shoot(200)
  println(SecretAgent.bullets) // see how many bullets left
  // 1700
}
//====================================================

// companion object access class private member
class Person(val name:String, private val superheroName:String)
object Person {
  def showMeInnerSecret(x:Person) = x.superheroName
}
object CompanionObjectAccessClassRunner extends App {
  val tom = new Person("Tom", "Superman")
  println(Person.showMeInnerSecret(tom))
  // Superman
}
//====================================================

// companion object as factory
import java.time._
// `protected` so test can access it
class Employee protected (val firstName:String, val lastName:String, val title:String, val hireDate:LocalDate)
object Employee {
  def create(firstName:String, lastName:String, title:String) =
    new Employee(firstName, lastName, title, LocalDate.now)
  def create(firstName:String, lastName:String, title:String, hireDate:LocalDate) =
    new Employee(firstName, lastName, title, hireDate)
}
object FactoryRunner extends App {
  val employee = Employee.create("Tom", "Lee", "Programmer")
  println(employee.hireDate)
}
```

## The Magic Apply Method

- If a method is called apply, you don't have to explicitly call it
- The method apply can be used in classes and objects
- This is very important concept. It is used extensively in Scala

```
// when call apply(), you can call it without method name `apply`
class Foo(x:Int) {
  def apply(y:Int) = x + y
}
object MagicApply extends App {
  val foo = new Foo(10)
  // cal with apply()
  println(foo.apply(20))
  // 30
  // when call apply(), you can call it without method name `apply`
  println(foo(21))
  // 31
}
//====================================================
// apply() method can make companion object acts like a `case class`
class Employee protected (val firstName:String, val lastName:String, val title:String, val hireDate:LocalDate)
object Employee {
  def apply(firstName:String, lastName:String, title:String) =
    new Employee(firstName, lastName, title, LocalDate.now)
  def apply(firstName:String, lastName:String, title:String, hireDate:LocalDate) =
    new Employee(firstName, lastName, title, hireDate)
}
case class Department(name:String)
object EmployeeDesignRunner extends App {
  // apply() method can make companion object acts like a `case class`
  val employee = Employee("Tom", "Lee", "Programmer")
  println(employee.hireDate)
  // 2018-04-10
  val applyDepartment = Department.apply("apply()")
  val department = Department("Sports")
  println(applyDepartment)
  // Department(apply())
  println(department)
  // Department(Sports)
}
```
