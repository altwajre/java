package com.company.app._08Option

case class Employee(firstName: String, middleName: Option[String], lastName: String) {
  // constructor
  def this(firstName: String, middleName: String, lastName: String) =
    this(firstName, Some(middleName), lastName)

  // constructor
  def this(firstName: String, lastName: String) =
    this(firstName, None, lastName)

  // constructor
  def this() = this("Unknown", "Unknown")
}

object Options extends App {
  val middleName = Some("Tom")
  println(middleName)
  // Some(Tom)

  val noMiddleName = None
  println(noMiddleName.getOrElse("No Middle name"))
  // No Middle name

  private val middle = new Employee("Tom", "Middle", "Lee")
  println(middle.middleName.getOrElse("No Middle name"))
  // Middle

  private val noMiddle = new Employee("Harry", "Wang")
  println(noMiddle.middleName.getOrElse("No Middle name"))
  // No Middle name

  private val noName = new Employee
  println(noName.firstName)
  // Unknown

  def peelTheMiddleName(x:Option[String]):String = {
    x match {
      case Some(name) => name
      case None => "no middle name"
    }
  }

  println(peelTheMiddleName(middle.middleName))
  // Middle
  println(peelTheMiddleName(noMiddle.middleName))
  // no middle name
  println(peelTheMiddleName(noName.middleName))
  // no middle name
}