val tom = new Employee("Tom", "Lee")
println(tom.firstName)

val newTom = tom.copy(lastName="NewTom")
println(newTom.lastName)
println(newTom.title)

//$ scala -nc EmployeeScript.scala
//Tom
//NewTom
//Programmer
