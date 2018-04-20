object MyMath {
  def add(x:Int, y:Int) = x + y
}

println(MyMath.add(2, 3))

case class Employee(firstName:String, lastName:String, title:String)

object tom extends Employee("Tom", "Lee", "Programmer")
println(tom.firstName)
println(tom.lastName)
println(tom.title)

//$ scala ObjectScript.scala
//5
//Tom
//Lee
//Programmer
