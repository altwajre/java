package com.company.app

import java.util.Properties

object LoadFromResources {
  def main(args: Array[String]) = {
    val props = new Properties
    // resource file should have the same package
    props.load(getClass.getResourceAsStream("person.properties"))
    val name = props.getProperty("person.name").toString
    val age = props.getProperty("person.age").toInt
    println(s"$name is $age years old")
  }

}
/*
"Tom" is 18 years old
 */
