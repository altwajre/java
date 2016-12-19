import scala.beans.BeanProperty

class Employee(@BeanProperty val firstName:String,
               @BeanProperty var lastName:String,
               val title:String = "Programmer"){
}
