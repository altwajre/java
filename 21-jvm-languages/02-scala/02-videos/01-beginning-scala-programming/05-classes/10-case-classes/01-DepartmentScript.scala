val toys1 = Department("Toys")
println(toys1)
val toys2 = new Department("Toys")
println(toys1 == toys2)
println(toys1.hashCode == toys2.hashCode)
val hardware = toys1.copy(name="Hardware")
println(hardware)

//$ scala -nc 01-DepartmentScript.scala
//Department(Toys)
//true
//true
//Department(Hardware)
