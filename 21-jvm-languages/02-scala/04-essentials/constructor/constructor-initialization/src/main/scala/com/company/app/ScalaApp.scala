package com.company.app

object ScalaApp {
  def main(args: Array[String]): Unit = {
    val tom = new Customer("Tom", "Wang")
    println(s"Scala says Hello to ${tom.name}!")
  }
}
