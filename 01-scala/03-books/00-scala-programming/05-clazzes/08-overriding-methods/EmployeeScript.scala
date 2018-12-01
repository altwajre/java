val mathematics = new Department("Mathematics")
val tom = new Manager("Tom", "Lee", "Mathematician", mathematics)
println(tom.department.name)

val tomEmployee:Employee = tom

println(tom.fullName)
println(tomEmployee.fullName)

//$ scalac Models.scala
//$ scala -nc EmployeeScript.scala
//Mathematics
//Tom Lee, Mathematics Manager
//Tom Lee, Mathematics Manager
