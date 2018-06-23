case class Employee(firstName:String, middleName: Option[String], lastName:String) {
  // constructor
  def this(firstName:String, middleName:String, lastName:String) =
    this(firstName, Some(middleName), lastName)
  // constructor
  def this(firstName:String, lastName:String) =
    this(firstName, None, lastName)
  // constructor
  def this() = this("Unknown", "Unknown")
}

object Options extends App {
  val middleName = Some("Antony")
  println(middleName.get)
  //Antony
  val middleName2:Option[String] = middleName
  val middleName3:Some[String] = middleName

  val noMiddleName = None
  val noMiddleName2:Option[String] = noMiddleName
  val noMiddleName3:Option[Nothing] = noMiddleName
  val noMiddleName4:None.type = noMiddleName
  println(noMiddleName.getOrElse("No middle name"))
  //No middle name

  println("#Employee")

  val tom = new Employee("Tom", "Antony", "Lee") //same as new Employee("Tom", Some("Antony"), "Lee")
  println(tom.middleName.getOrElse("No middle name"))
  //Antony
  val dick = new Employee("Dick", "Wang") //same as new Employee("Dick", None, "Wang")
  println(dick.middleName)
  //None
  val strangePerson = new Employee
  println(strangePerson.middleName)
  //None

  def peelTheMiddleName(x:Option[String]):String = {
    x match {
      case Some(name) => name
      case None => "No middle name"
    }
  }

  println("#peelTheMiddleName")
  println(peelTheMiddleName(tom.middleName))
  //Antony
  println(peelTheMiddleName(dick.middleName))
  //No middle name
  println(peelTheMiddleName(strangePerson.middleName))
  //No middle name
}
