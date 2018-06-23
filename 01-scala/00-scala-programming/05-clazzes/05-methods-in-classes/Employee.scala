import scala.beans.BeanProperty

class Employee(@BeanProperty val firstName:String,
               @BeanProperty val lastName:String,
               val title:String = "Programmer"){
  def fullName = s"$firstName $lastName"

  def copy(firstName:String=this.firstName,
           lastName:String=this.lastName,
           title:String=this.title) = {
    new Employee(firstName, lastName, title)
  }
}
