val intBox = Box(8)
val coupleInStringBox:Box[Couple[Int,String]] = intBox.coupleWith("Scala")
println(coupleInStringBox.t.first)
println(coupleInStringBox.t.second)

val employeeCouple = Couple(new Employee("A", "B"), new Employee("B", "A"))
println(employeeCouple)
println(employeeCouple.swap)

//$ scalac Employee.scala
//$ scalac Couple.scala
//$ scalac Box.scala
//$ scala -nc BoxScript.scala
//8
//Scala
//Couple(Employee(A,B),Employee(B,A))
//Couple(Employee(B,A),Employee(A,B))
