val intBox = new Box(1) //Box[Int]
val intBox2:Box[Int] = Box(1) //Box[Int]
val intBox3 = Box(1):Box[Int] //Coercion Box[Int]; Coercion means force
val intResult:Int = intBox3.t

val stringBox = new Box("Hello")
val stringBoxExplicit = Box[String]("Hello")

val managerBox = Box[Employee](new Manager("Tom", "Lee", "Programmer",
  new Department("Programming"))) //Box(Employee)

val doubleBoxBox = Box(Box(4.0)) //Box[Box[Double]]
val doubleResult:Double = doubleBoxBox.t.t
println(doubleResult)

//$ scalac Models.scala
//$ scalac Box.scala
//$ scala -nc BoxScript.scala
//4.0
