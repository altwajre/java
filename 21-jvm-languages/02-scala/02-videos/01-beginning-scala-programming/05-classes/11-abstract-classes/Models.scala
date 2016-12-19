abstract class Person{
  def firstName:String
  def lastName:String
}

case class Employee(firstName:String, lastName:String) extends Person {

}
