val tom = new Employee("Tom", "Lee")
println(tom.fullName)

val anotherTom = new Employee("Tom", "Lee", "Programmer")
println(anotherTom.fullName)

println(tom == anotherTom) // true
println(tom != anotherTom) // false
println(tom eq anotherTom) // false

println("#hashCode")
val evenAnotherTom = anotherTom
println(anotherTom eq evenAnotherTom) // true
println(tom.hashCode == evenAnotherTom.hashCode) // true

//$ scala -nc EmployeeScript.scala
//Tom Lee
//Tom Lee
//true
//false
//false
//#hashCode
//true
//true
