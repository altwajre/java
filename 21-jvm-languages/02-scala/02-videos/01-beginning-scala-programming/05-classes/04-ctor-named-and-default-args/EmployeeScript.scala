val tom = new Employee(lastName="Lee", firstName="Tom")
println(s"First name is ${tom.firstName}")
println(s"Last name is ${tom.lastName}")

//$ scalac Employee.scala
//$ scala -nc EmployeeScript.scala
//First name is Tom
//Last name is Lee
