try {
  new Employee("Tom", "", "Singer")
} catch {
  case iae:IllegalArgumentException => println(iae.getMessage)
} finally {
  println("Continuing with our program 1")
}

try {
  new Employee("Tom", "Lee", "Senior C Programmer")
} catch {
  case iae:IllegalArgumentException => println(iae.getMessage)
} finally {
  println("Continuing with our program 2")
}

//$ scalac Employee.scala
//$ scala -nc EmployeeScript.scala
//requirement failed: Last Name cannot be empty
//Continuing with our program 1
//Title cannot contain Junior or Senior
//Continuing with our program 2
