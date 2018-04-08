package com.company.app

object ScalaHello {
  def main(args: Array[String]): Unit = {
    val tom = new Person()
    tom.setName("Tom")
    tom.setAge(28)
    println(s"Scala says Hello, ${tom.getName}!")
  }
}
