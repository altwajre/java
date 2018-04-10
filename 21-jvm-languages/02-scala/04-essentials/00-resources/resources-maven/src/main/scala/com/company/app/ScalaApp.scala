package com.company.app

import scala.io.Source

object ScalaApp {
  def main(args: Array[String]): Unit = {
    val stream = getClass.getResourceAsStream("/data/user.json")
    val userJson = Source.fromInputStream(stream).mkString
    println(userJson)
  }
}
