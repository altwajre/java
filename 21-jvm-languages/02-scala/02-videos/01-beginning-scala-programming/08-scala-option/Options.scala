case class Employee(firstName:String, middleName: Option[String], lastName:String) {
  def this(firstName:String, middleName:String, lastName:String) =
    this(firstName, Some(middleName), lastName)
  def this(firstName:String, lastName:String) =
  this(firstName, None, lastName)
  def this() = this("Unknown", "Unknown")
}

object Options extends App {
  val middleName = Some("Antony")
  val middleName2:Option[String] = middleName
  val middleName3:Some[String] = middleName

  val noMiddleName = None
  val noMiddleName2:Option[String] = noMiddleName
  val noMiddleName3:Option[Nothing] = noMiddleName
  val noMiddleName4:None.type = noMiddleName

  println(middleName.get)
  println(noMiddleName.getOrElse("No middle name"))

  println("#Employee")

  val tom = new Employee("Tom", "Antony", "Lee") //same as new Employee("Tom", Some("Antony"), "Lee")
  val dick = new Employee("Dick", "Wang") //same as new Employee("Dick", None, "Wang")
  val strangePerson = new Employee

  println(tom.middleName.getOrElse("No middle name"))
  println(dick.middleName)
  println(strangePerson.middleName)

  def peelTheMiddleName(x:Option[String]):String = {
    x match {
      case Some(name) => name
      case None => "No middle name"
    }
  }

  println("#peelTheMiddleName")
  println(peelTheMiddleName(tom.middleName))
  println(peelTheMiddleName(dick.middleName))
  println(peelTheMiddleName(strangePerson.middleName))
}

//$ scala Options.scala
//Antony
//No middle name
//#Employee
//Antony
//None
//None
//#peelTheMiddleName
//Antony
//No middle name
//No middle name
