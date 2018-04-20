import scala.beans.BeanProperty

class Employee(@BeanProperty val firstName:String,
               @BeanProperty var lastName:String,
               val title:String){

  // Atypical form with side effect without an e
//  def this(firstName:String, lastName:String) {
//    this(firstName, lastName, "Programmer")
//    println("Side Effect")
//  }

  // Atypical form with side effect
//  def this(firstName:String, lastName:String) = {
//    this(firstName, lastName, "Programmer")
//    println("Side Effect")
//  }

  //Typical Form
  def this(firstName:String, lastName:String) = this(firstName, lastName, "Programmer")
}

