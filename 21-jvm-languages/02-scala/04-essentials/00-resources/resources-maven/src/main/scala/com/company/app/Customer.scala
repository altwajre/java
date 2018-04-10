package com.company.app

class Customer (firstName: String, lastName: String) {
  val _name = firstName + " " + lastName
  println(_name)
  // Getter
  def name = _name
}
