val ada = new Employee("Ada", "Lovelace")

println(ada.firstName)

ada.lastName = "Lee"
println(ada.lastName)

println(ada.title)

//$ scalac Employee.scala
//$ scala -nc EmployeeScript.scala
//Ada
//Lee
//Programmer
