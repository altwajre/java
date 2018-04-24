val mathematics = new Department("Mathematics")
val tom = new Manager("Tom", "Lee", "Mathematician", mathematics)
println(tom.department.name)

val tomEmployee:Employee = tom

println(tom.firstName)
println(tomEmployee.firstName)

println("#polymorphism")
def extractFirstName(e:Employee) = e.firstName

println(extractFirstName(tom))
println(extractFirstName(tomEmployee))

//$ scalac Models.scala
//$ scala -nc EmployeeScript.scala
//Mathematics
//Tom
//Tom
//#polymorphism
//Tom
//Tom
