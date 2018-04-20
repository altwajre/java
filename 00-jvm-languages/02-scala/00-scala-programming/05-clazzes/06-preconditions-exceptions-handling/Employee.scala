import scala.beans.BeanProperty

class Employee(@BeanProperty val firstName:String,
               @BeanProperty val lastName:String,
               val title:String = "Programmer"){
  require(firstName.nonEmpty, "First Name cannot be empty")
  require(lastName.nonEmpty, "Last Name cannot be empty")
  require(title.nonEmpty, "Title cannot be empty")

  if(title.contains("Senior") || title.contains("Junior"))
    throw new IllegalArgumentException("Title cannot contain Junior or Senior")
}
